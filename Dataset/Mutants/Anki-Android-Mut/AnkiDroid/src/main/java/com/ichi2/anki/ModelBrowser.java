/**
 * *************************************************************************************
 *  Copyright (c) 2015 Ryan Annis <squeenix@live.ca>                                     *
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anki.dialogs.ConfirmationDialog;
import com.ichi2.anki.dialogs.ModelBrowserContextMenu;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListenerWithContext;
import com.ichi2.async.TaskManager;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.StdModels;
import com.ichi2.ui.FixedEditText;
import com.ichi2.utils.Triple;
import com.ichi2.widget.WidgetStatus;
import java.util.ArrayList;
import java.util.Random;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.LEFT;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ModelBrowser extends AnkiActivity {

    public static final int REQUEST_TEMPLATE_EDIT = 3;

    DisplayPairAdapter mModelDisplayAdapter;

    private ListView mModelListView;

    // Of the currently selected model
    private long mCurrentID;

    private int mModelListPosition;

    // Used exclusively to display model name
    private ArrayList<Model> mModels;

    private ArrayList<Integer> mCardCounts;

    private ArrayList<Long> mModelIds;

    private ArrayList<DisplayPair> mModelDisplayList;

    private Collection col;

    private ActionBar mActionBar;

    // Dialogue used in renaming
    private EditText mModelNameInput;

    private ModelBrowserContextMenu mContextMenu;

    private ArrayList<String> mNewModelNames;

    /*
     * Displays the loading bar when loading the mModels and displaying them
     * loading bar is necessary because card count per model is not cached *
     */
    private LoadingModelsHandler loadingModelsHandler() {
        return new LoadingModelsHandler(this);
    }

    private static class LoadingModelsHandler extends TaskListenerWithContext<ModelBrowser, Void, Pair<ArrayList<Model>, ArrayList<Integer>>> {

        public LoadingModelsHandler(ModelBrowser browser) {
            super(browser);
        }

        @Override
        public void actualOnCancelled(@NonNull ModelBrowser browser) {
            if (!ListenerUtil.mutListener.listen(8869)) {
                browser.hideProgressBar();
            }
        }

        @Override
        public void actualOnPreExecute(@NonNull ModelBrowser browser) {
            if (!ListenerUtil.mutListener.listen(8870)) {
                browser.showProgressBar();
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull ModelBrowser browser, Pair<ArrayList<Model>, ArrayList<Integer>> result) {
            if (!ListenerUtil.mutListener.listen(8871)) {
                if (result == null) {
                    throw new RuntimeException();
                }
            }
            if (!ListenerUtil.mutListener.listen(8872)) {
                browser.hideProgressBar();
            }
            if (!ListenerUtil.mutListener.listen(8873)) {
                browser.mModels = result.first;
            }
            if (!ListenerUtil.mutListener.listen(8874)) {
                browser.mCardCounts = result.second;
            }
            if (!ListenerUtil.mutListener.listen(8875)) {
                browser.fillModelList();
            }
        }
    }

    /*
     * Displays loading bar when deleting a model loading bar is needed
     * because deleting a model also deletes all of the associated cards/notes *
     */
    private DeleteModelHandler deleteModelHandler() {
        return new DeleteModelHandler(this);
    }

    private static class DeleteModelHandler extends TaskListenerWithContext<ModelBrowser, Void, Boolean> {

        public DeleteModelHandler(ModelBrowser browser) {
            super(browser);
        }

        @Override
        public void actualOnPreExecute(@NonNull ModelBrowser browser) {
            if (!ListenerUtil.mutListener.listen(8876)) {
                browser.showProgressBar();
            }
        }

        @Override
        public void actualOnPostExecute(@NonNull ModelBrowser browser, Boolean result) {
            if (!ListenerUtil.mutListener.listen(8877)) {
                if (!result) {
                    throw new RuntimeException();
                }
            }
            if (!ListenerUtil.mutListener.listen(8878)) {
                browser.hideProgressBar();
            }
            if (!ListenerUtil.mutListener.listen(8879)) {
                browser.refreshList();
            }
        }
    }

    /*
     * Listens to long hold context menu for main list items
     */
    private final MaterialDialog.ListCallback mContextMenuListener = (materialDialog, view, selection, charSequence) -> {
        switch(selection) {
            case ModelBrowserContextMenu.MODEL_DELETE:
                deleteModelDialog();
                break;
            case ModelBrowserContextMenu.MODEL_RENAME:
                renameModelDialog();
                break;
            case ModelBrowserContextMenu.MODEL_TEMPLATE:
                openTemplateEditor();
                break;
        }
    };

    // ----------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8880)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8881)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8882)) {
            setContentView(R.layout.model_browser);
        }
        if (!ListenerUtil.mutListener.listen(8883)) {
            mModelListView = findViewById(R.id.note_type_browser_list);
        }
        if (!ListenerUtil.mutListener.listen(8884)) {
            enableToolbar();
        }
        if (!ListenerUtil.mutListener.listen(8885)) {
            mActionBar = getSupportActionBar();
        }
        if (!ListenerUtil.mutListener.listen(8886)) {
            startLoadingCollection();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(8887)) {
            Timber.d("onResume()");
        }
        if (!ListenerUtil.mutListener.listen(8888)) {
            super.onResume();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(8889)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(8890)) {
            getMenuInflater().inflate(R.menu.model_browser, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(8893)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(8892)) {
                    onBackPressed();
                }
                return true;
            } else if (itemId == R.id.action_add_new_note_type) {
                if (!ListenerUtil.mutListener.listen(8891)) {
                    addNewNoteTypeDialog();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(8894)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(8897)) {
            if (!isFinishing()) {
                if (!ListenerUtil.mutListener.listen(8895)) {
                    WidgetStatus.update(this);
                }
                if (!ListenerUtil.mutListener.listen(8896)) {
                    UIUtils.saveCollectionInBackground();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(8898)) {
            TaskManager.cancelAllTasks(CollectionTask.CountModels.class);
        }
        if (!ListenerUtil.mutListener.listen(8899)) {
            super.onDestroy();
        }
    }

    // ----------------------------------------------------------------------------
    @Override
    public void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(8900)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(8901)) {
            this.col = col;
        }
        if (!ListenerUtil.mutListener.listen(8902)) {
            TaskManager.launchCollectionTask(new CollectionTask.CountModels(), loadingModelsHandler());
        }
    }

    /*
     * Fills the main list view with model names.
     * Handles filling the ArrayLists and attaching
     * ArrayAdapters to main ListView
     */
    private void fillModelList() {
        if (!ListenerUtil.mutListener.listen(8903)) {
            // Anonymous class for handling list item clicks
            mModelDisplayList = new ArrayList<>(mModels.size());
        }
        if (!ListenerUtil.mutListener.listen(8904)) {
            mModelIds = new ArrayList<>(mModels.size());
        }
        if (!ListenerUtil.mutListener.listen(8912)) {
            {
                long _loopCounter141 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8911) ? (i >= mModels.size()) : (ListenerUtil.mutListener.listen(8910) ? (i <= mModels.size()) : (ListenerUtil.mutListener.listen(8909) ? (i > mModels.size()) : (ListenerUtil.mutListener.listen(8908) ? (i != mModels.size()) : (ListenerUtil.mutListener.listen(8907) ? (i == mModels.size()) : (i < mModels.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter141", ++_loopCounter141);
                    if (!ListenerUtil.mutListener.listen(8905)) {
                        mModelIds.add(mModels.get(i).getLong("id"));
                    }
                    if (!ListenerUtil.mutListener.listen(8906)) {
                        mModelDisplayList.add(new DisplayPair(mModels.get(i).getString("name"), mCardCounts.get(i)));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8913)) {
            mModelDisplayAdapter = new DisplayPairAdapter(this, mModelDisplayList);
        }
        if (!ListenerUtil.mutListener.listen(8914)) {
            mModelListView.setAdapter(mModelDisplayAdapter);
        }
        if (!ListenerUtil.mutListener.listen(8915)) {
            mModelListView.setOnItemClickListener((parent, view, position, id) -> {
                long noteTypeID = mModelIds.get(position);
                mModelListPosition = position;
                Intent noteOpenIntent = new Intent(ModelBrowser.this, ModelFieldEditor.class);
                noteOpenIntent.putExtra("title", mModelDisplayList.get(position).getName());
                noteOpenIntent.putExtra("noteTypeID", noteTypeID);
                startActivityForResultWithAnimation(noteOpenIntent, 0, LEFT);
            });
        }
        if (!ListenerUtil.mutListener.listen(8916)) {
            mModelListView.setOnItemLongClickListener((parent, view, position, id) -> {
                String cardName = mModelDisplayList.get(position).getName();
                mCurrentID = mModelIds.get(position);
                mModelListPosition = position;
                mContextMenu = ModelBrowserContextMenu.newInstance(cardName, mContextMenuListener);
                showDialogFragment(mContextMenu);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(8917)) {
            updateSubtitleText();
        }
    }

    /*
     * Updates the subtitle showing the amount of mModels available
     * ONLY CALL THIS AFTER initializing the main list
     */
    private void updateSubtitleText() {
        int count = mModelIds.size();
        if (!ListenerUtil.mutListener.listen(8918)) {
            mActionBar.setSubtitle(getResources().getQuantityString(R.plurals.model_browser_types_available, count, count));
        }
    }

    /*
     *Creates the dialogue box to select a note type, add a name, and then clone it
     */
    private void addNewNoteTypeDialog() {
        String add = getResources().getString(R.string.model_browser_add_add);
        String clone = getResources().getString(R.string.model_browser_add_clone);
        // Populates arrayadapters listing the mModels (includes prefixes/suffixes)
        int existingModelSize = (mModels == null) ? 0 : mModels.size();
        int stdModelSize = StdModels.stdModels.length;
        ArrayList<String> newModelLabels = new ArrayList<>((ListenerUtil.mutListener.listen(8922) ? (existingModelSize % stdModelSize) : (ListenerUtil.mutListener.listen(8921) ? (existingModelSize / stdModelSize) : (ListenerUtil.mutListener.listen(8920) ? (existingModelSize * stdModelSize) : (ListenerUtil.mutListener.listen(8919) ? (existingModelSize - stdModelSize) : (existingModelSize + stdModelSize))))));
        ArrayList<String> existingModelsNames = new ArrayList<>(existingModelSize);
        if (!ListenerUtil.mutListener.listen(8923)) {
            // Used to fetch model names
            mNewModelNames = new ArrayList<>(stdModelSize);
        }
        if (!ListenerUtil.mutListener.listen(8926)) {
            {
                long _loopCounter142 = 0;
                for (StdModels StdModels : StdModels.stdModels) {
                    ListenerUtil.loopListener.listen("_loopCounter142", ++_loopCounter142);
                    String defaultName = StdModels.getDefaultName();
                    if (!ListenerUtil.mutListener.listen(8924)) {
                        newModelLabels.add(String.format(add, defaultName));
                    }
                    if (!ListenerUtil.mutListener.listen(8925)) {
                        mNewModelNames.add(defaultName);
                    }
                }
            }
        }
        final int numStdModels = newModelLabels.size();
        if (!ListenerUtil.mutListener.listen(8931)) {
            if (mModels != null) {
                if (!ListenerUtil.mutListener.listen(8930)) {
                    {
                        long _loopCounter143 = 0;
                        for (Model model : mModels) {
                            ListenerUtil.loopListener.listen("_loopCounter143", ++_loopCounter143);
                            String name = model.getString("name");
                            if (!ListenerUtil.mutListener.listen(8927)) {
                                newModelLabels.add(String.format(clone, name));
                            }
                            if (!ListenerUtil.mutListener.listen(8928)) {
                                mNewModelNames.add(name);
                            }
                            if (!ListenerUtil.mutListener.listen(8929)) {
                                existingModelsNames.add(name);
                            }
                        }
                    }
                }
            }
        }
        final Spinner addSelectionSpinner = new Spinner(this);
        ArrayAdapter<String> mNewModelAdapter = new ArrayAdapter<>(this, R.layout.dropdown_deck_item, newModelLabels);
        if (!ListenerUtil.mutListener.listen(8932)) {
            addSelectionSpinner.setAdapter(mNewModelAdapter);
        }
        if (!ListenerUtil.mutListener.listen(8933)) {
            new MaterialDialog.Builder(this).title(R.string.model_browser_add).positiveText(R.string.dialog_ok).customView(addSelectionSpinner, true).onPositive((dialog, which) -> {
                mModelNameInput = new FixedEditText(ModelBrowser.this);
                mModelNameInput.setSingleLine();
                final boolean isStdModel = addSelectionSpinner.getSelectedItemPosition() < numStdModels;
                // Try to find a unique model name. Add "clone" if cloning, and random digits if necessary.
                String suggestedName = mNewModelNames.get(addSelectionSpinner.getSelectedItemPosition());
                if (!isStdModel) {
                    suggestedName += " " + getResources().getString(R.string.model_clone_suffix);
                }
                if (existingModelsNames.contains(suggestedName)) {
                    suggestedName = randomizeName(suggestedName);
                }
                mModelNameInput.setText(suggestedName);
                mModelNameInput.setSelection(mModelNameInput.getText().length());
                // Create textbox to name new model
                new MaterialDialog.Builder(ModelBrowser.this).title(R.string.model_browser_add).positiveText(R.string.dialog_ok).customView(mModelNameInput, true).onPositive((innerDialog, innerWhich) -> {
                    String modelName = mModelNameInput.getText().toString();
                    addNewNoteType(modelName, addSelectionSpinner.getSelectedItemPosition());
                }).negativeText(R.string.dialog_cancel).show();
            }).negativeText(R.string.dialog_cancel).show();
        }
    }

    /**
     * Add a new note type
     * @param modelName name of the new model
     * @param position position in dialog the user selected to add / clone the model type from
     */
    private void addNewNoteType(String modelName, int position) {
        Model model;
        if ((ListenerUtil.mutListener.listen(8938) ? (modelName.length() >= 0) : (ListenerUtil.mutListener.listen(8937) ? (modelName.length() <= 0) : (ListenerUtil.mutListener.listen(8936) ? (modelName.length() < 0) : (ListenerUtil.mutListener.listen(8935) ? (modelName.length() != 0) : (ListenerUtil.mutListener.listen(8934) ? (modelName.length() == 0) : (modelName.length() > 0))))))) {
            int nbStdModels = StdModels.stdModels.length;
            if ((ListenerUtil.mutListener.listen(8944) ? (position >= nbStdModels) : (ListenerUtil.mutListener.listen(8943) ? (position <= nbStdModels) : (ListenerUtil.mutListener.listen(8942) ? (position > nbStdModels) : (ListenerUtil.mutListener.listen(8941) ? (position != nbStdModels) : (ListenerUtil.mutListener.listen(8940) ? (position == nbStdModels) : (position < nbStdModels))))))) {
                model = StdModels.stdModels[position].add(col);
            } else {
                // Model that is being cloned
                Model oldModel = mModels.get((ListenerUtil.mutListener.listen(8948) ? (position % nbStdModels) : (ListenerUtil.mutListener.listen(8947) ? (position / nbStdModels) : (ListenerUtil.mutListener.listen(8946) ? (position * nbStdModels) : (ListenerUtil.mutListener.listen(8945) ? (position + nbStdModels) : (position - nbStdModels)))))).deepClone();
                Model newModel = StdModels.basicModel.add(col);
                if (!ListenerUtil.mutListener.listen(8949)) {
                    oldModel.put("id", newModel.getLong("id"));
                }
                model = oldModel;
            }
            if (!ListenerUtil.mutListener.listen(8950)) {
                model.put("name", modelName);
            }
            if (!ListenerUtil.mutListener.listen(8951)) {
                col.getModels().update(model);
            }
            if (!ListenerUtil.mutListener.listen(8952)) {
                fullRefresh();
            }
        } else {
            if (!ListenerUtil.mutListener.listen(8939)) {
                showToast(getResources().getString(R.string.toast_empty_name));
            }
        }
    }

    /*
     * Displays a confirmation box asking if you want to delete the note type and then deletes it if confirmed
     */
    private void deleteModelDialog() {
        if (!ListenerUtil.mutListener.listen(8968)) {
            if ((ListenerUtil.mutListener.listen(8957) ? (mModelIds.size() >= 1) : (ListenerUtil.mutListener.listen(8956) ? (mModelIds.size() <= 1) : (ListenerUtil.mutListener.listen(8955) ? (mModelIds.size() < 1) : (ListenerUtil.mutListener.listen(8954) ? (mModelIds.size() != 1) : (ListenerUtil.mutListener.listen(8953) ? (mModelIds.size() == 1) : (mModelIds.size() > 1))))))) {
                Runnable confirm = () -> {
                    col.modSchemaNoCheck();
                    deleteModel();
                    dismissContextMenu();
                };
                Runnable cancel = this::dismissContextMenu;
                try {
                    if (!ListenerUtil.mutListener.listen(8963)) {
                        col.modSchema();
                    }
                    ConfirmationDialog d = new ConfirmationDialog();
                    if (!ListenerUtil.mutListener.listen(8964)) {
                        d.setArgs(getResources().getString(R.string.model_delete_warning));
                    }
                    if (!ListenerUtil.mutListener.listen(8965)) {
                        d.setConfirm(confirm);
                    }
                    if (!ListenerUtil.mutListener.listen(8966)) {
                        d.setCancel(cancel);
                    }
                    if (!ListenerUtil.mutListener.listen(8967)) {
                        ModelBrowser.this.showDialogFragment(d);
                    }
                } catch (ConfirmModSchemaException e) {
                    ConfirmationDialog c = new ConfirmationDialog();
                    if (!ListenerUtil.mutListener.listen(8959)) {
                        c.setArgs(getResources().getString(R.string.full_sync_confirmation));
                    }
                    if (!ListenerUtil.mutListener.listen(8960)) {
                        c.setConfirm(confirm);
                    }
                    if (!ListenerUtil.mutListener.listen(8961)) {
                        c.setCancel(cancel);
                    }
                    if (!ListenerUtil.mutListener.listen(8962)) {
                        showDialogFragment(c);
                    }
                }
            } else // Prevent users from deleting last model
            {
                if (!ListenerUtil.mutListener.listen(8958)) {
                    showToast(getString(R.string.toast_last_model));
                }
            }
        }
    }

    /*
     * Displays a confirmation box asking if you want to rename the note type and then renames it if confirmed
     */
    private void renameModelDialog() {
        if (!ListenerUtil.mutListener.listen(8969)) {
            mModelNameInput = new FixedEditText(this);
        }
        if (!ListenerUtil.mutListener.listen(8970)) {
            mModelNameInput.setSingleLine(true);
        }
        if (!ListenerUtil.mutListener.listen(8971)) {
            mModelNameInput.setText(mModels.get(mModelListPosition).getString("name"));
        }
        if (!ListenerUtil.mutListener.listen(8972)) {
            mModelNameInput.setSelection(mModelNameInput.getText().length());
        }
        if (!ListenerUtil.mutListener.listen(8973)) {
            new MaterialDialog.Builder(this).title(R.string.rename_model).positiveText(R.string.rename).negativeText(R.string.dialog_cancel).customView(mModelNameInput, true).onPositive((dialog, which) -> {
                Model model = mModels.get(mModelListPosition);
                String deckName = mModelNameInput.getText().toString().replaceAll("[\"\\n\\r]", "");
                if (deckName.length() > 0) {
                    model.put("name", deckName);
                    col.getModels().update(model);
                    mModels.get(mModelListPosition).put("name", deckName);
                    mModelDisplayList.set(mModelListPosition, new DisplayPair(mModels.get(mModelListPosition).getString("name"), mCardCounts.get(mModelListPosition)));
                    refreshList();
                } else {
                    showToast(getResources().getString(R.string.toast_empty_name));
                }
            }).show();
        }
    }

    private void dismissContextMenu() {
        if (!ListenerUtil.mutListener.listen(8976)) {
            if (mContextMenu != null) {
                if (!ListenerUtil.mutListener.listen(8974)) {
                    mContextMenu.dismiss();
                }
                if (!ListenerUtil.mutListener.listen(8975)) {
                    mContextMenu = null;
                }
            }
        }
    }

    /*
     * Opens the Template Editor (Card Editor) to allow
     * the user to edit the current note's templates.
     */
    private void openTemplateEditor() {
        Intent intent = new Intent(this, CardTemplateEditor.class);
        if (!ListenerUtil.mutListener.listen(8977)) {
            intent.putExtra("modelId", mCurrentID);
        }
        if (!ListenerUtil.mutListener.listen(8978)) {
            startActivityForResultWithAnimation(intent, REQUEST_TEMPLATE_EDIT, LEFT);
        }
    }

    /*
     * Updates the ArrayAdapters for the main ListView.
     * ArrayLists must be manually updated.
     */
    private void refreshList() {
        if (!ListenerUtil.mutListener.listen(8979)) {
            mModelDisplayAdapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(8980)) {
            updateSubtitleText();
        }
    }

    /*
     * Reloads everything
     */
    private void fullRefresh() {
        if (!ListenerUtil.mutListener.listen(8981)) {
            TaskManager.launchCollectionTask(new CollectionTask.CountModels(), loadingModelsHandler());
        }
    }

    /*
     * Deletes the currently selected model
     */
    private void deleteModel() {
        if (!ListenerUtil.mutListener.listen(8982)) {
            TaskManager.launchCollectionTask(new CollectionTask.DeleteModel(mCurrentID), deleteModelHandler());
        }
        if (!ListenerUtil.mutListener.listen(8983)) {
            mModels.remove(mModelListPosition);
        }
        if (!ListenerUtil.mutListener.listen(8984)) {
            mModelIds.remove(mModelListPosition);
        }
        if (!ListenerUtil.mutListener.listen(8985)) {
            mModelDisplayList.remove(mModelListPosition);
        }
        if (!ListenerUtil.mutListener.listen(8986)) {
            mCardCounts.remove(mModelListPosition);
        }
        if (!ListenerUtil.mutListener.listen(8987)) {
            refreshList();
        }
    }

    /*
     * Generates a random alphanumeric sequence of 6 characters
     * Used to append to the end of new note types to dissuade
     * User from reusing names (which are technically not unique however
     */
    private String randomizeName(String s) {
        char[] charSet = "123456789abcdefghijklmnopqrstuvqxwzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] randomString = new char[6];
        Random random = new Random();
        if (!ListenerUtil.mutListener.listen(8994)) {
            {
                long _loopCounter144 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8993) ? (i >= 6) : (ListenerUtil.mutListener.listen(8992) ? (i <= 6) : (ListenerUtil.mutListener.listen(8991) ? (i > 6) : (ListenerUtil.mutListener.listen(8990) ? (i != 6) : (ListenerUtil.mutListener.listen(8989) ? (i == 6) : (i < 6)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter144", ++_loopCounter144);
                    int randomIndex = random.nextInt(charSet.length);
                    if (!ListenerUtil.mutListener.listen(8988)) {
                        randomString[i] = charSet[randomIndex];
                    }
                }
            }
        }
        return s + " " + new String(randomString);
    }

    private void showToast(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        if (!ListenerUtil.mutListener.listen(8995)) {
            toast.show();
        }
    }

    /*
     * Used so that the main ListView is able to display the number of notes using the model
     * along with the name.
     */
    public static class DisplayPair {

        private final String name;

        private final int count;

        public DisplayPair(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        @Override
        @NonNull
        public String toString() {
            return getName();
        }
    }

    /*
     * For display in the main list via an ArrayAdapter
     */
    public class DisplayPairAdapter extends ArrayAdapter<DisplayPair> {

        public DisplayPairAdapter(Context context, ArrayList<DisplayPair> items) {
            super(context, R.layout.model_browser_list_item, R.id.model_list_item_1, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            DisplayPair item = getItem(position);
            if (!ListenerUtil.mutListener.listen(8997)) {
                if (convertView == null) {
                    if (!ListenerUtil.mutListener.listen(8996)) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_browser_list_item, parent, false);
                    }
                }
            }
            TextView tvName = convertView.findViewById(R.id.model_list_item_1);
            TextView tvHome = convertView.findViewById(R.id.model_list_item_2);
            int count = item.getCount();
            if (!ListenerUtil.mutListener.listen(8998)) {
                tvName.setText(item.getName());
            }
            if (!ListenerUtil.mutListener.listen(8999)) {
                tvHome.setText(getResources().getQuantityString(R.plurals.model_browser_of_type, count, count));
            }
            return convertView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(9000)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(9007)) {
            if ((ListenerUtil.mutListener.listen(9005) ? (requestCode >= REQUEST_TEMPLATE_EDIT) : (ListenerUtil.mutListener.listen(9004) ? (requestCode <= REQUEST_TEMPLATE_EDIT) : (ListenerUtil.mutListener.listen(9003) ? (requestCode > REQUEST_TEMPLATE_EDIT) : (ListenerUtil.mutListener.listen(9002) ? (requestCode < REQUEST_TEMPLATE_EDIT) : (ListenerUtil.mutListener.listen(9001) ? (requestCode != REQUEST_TEMPLATE_EDIT) : (requestCode == REQUEST_TEMPLATE_EDIT))))))) {
                if (!ListenerUtil.mutListener.listen(9006)) {
                    TaskManager.launchCollectionTask(new CollectionTask.CountModels(), loadingModelsHandler());
                }
            }
        }
    }
}
