/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities.ballot;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.adapters.ballot.BallotWizard1Adapter;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import static com.google.android.material.timepicker.TimeFormat.CLOCK_12H;
import static com.google.android.material.timepicker.TimeFormat.CLOCK_24H;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotWizardFragment1 extends BallotWizardFragment implements BallotWizardActivity.BallotWizardCallback, BallotWizard1Adapter.OnChoiceListener, TextEntryDialog.TextEntryDialogClickListener {

    private static final String DIALOG_TAG_SELECT_DATE = "selectDate";

    private static final String DIALOG_TAG_SELECT_TIME = "selectTime";

    private static final String DIALOG_TAG_SELECT_DATETIME = "selectDateTime";

    private static final String DIALOG_TAG_EDIT_ANSWER = "editAnswer";

    private RecyclerView choiceRecyclerView;

    private List<BallotChoiceModel> ballotChoiceModelList;

    private BallotWizard1Adapter listAdapter = null;

    private ImageButton createChoiceButton;

    private EditText createChoiceEditText;

    private Long originalTimeInUtc = null;

    private LinearLayoutManager choiceRecyclerViewLayoutManager;

    private int lastVisibleBallotPosition;

    private int editItemPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ballot_wizard1, container, false);
        if (!ListenerUtil.mutListener.listen(548)) {
            this.choiceRecyclerView = rootView.findViewById(R.id.ballot_list);
        }
        if (!ListenerUtil.mutListener.listen(549)) {
            this.choiceRecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(550)) {
            this.choiceRecyclerView.setLayoutManager(choiceRecyclerViewLayoutManager);
        }
        if (!ListenerUtil.mutListener.listen(559)) {
            this.choiceRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (!ListenerUtil.mutListener.listen(558)) {
                        if ((ListenerUtil.mutListener.listen(555) ? (bottom >= oldBottom) : (ListenerUtil.mutListener.listen(554) ? (bottom <= oldBottom) : (ListenerUtil.mutListener.listen(553) ? (bottom > oldBottom) : (ListenerUtil.mutListener.listen(552) ? (bottom != oldBottom) : (ListenerUtil.mutListener.listen(551) ? (bottom == oldBottom) : (bottom < oldBottom))))))) {
                            if (!ListenerUtil.mutListener.listen(557)) {
                                choiceRecyclerView.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(556)) {
                                                choiceRecyclerView.smoothScrollToPosition(lastVisibleBallotPosition);
                                            }
                                        } catch (IllegalArgumentException ignored) {
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(563)) {
            this.choiceRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    if (!ListenerUtil.mutListener.listen(560)) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }
                    if (!ListenerUtil.mutListener.listen(562)) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            if (!ListenerUtil.mutListener.listen(561)) {
                                lastVisibleBallotPosition = choiceRecyclerViewLayoutManager.findLastVisibleItemPosition();
                            }
                        }
                    }
                }
            });
        }
        int moveUpDown = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        ItemTouchHelper.Callback swipeCallback = new ItemTouchHelper.SimpleCallback(moveUpDown, 0) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (!ListenerUtil.mutListener.listen(591)) {
                    if ((ListenerUtil.mutListener.listen(568) ? (fromPosition >= toPosition) : (ListenerUtil.mutListener.listen(567) ? (fromPosition <= toPosition) : (ListenerUtil.mutListener.listen(566) ? (fromPosition > toPosition) : (ListenerUtil.mutListener.listen(565) ? (fromPosition != toPosition) : (ListenerUtil.mutListener.listen(564) ? (fromPosition == toPosition) : (fromPosition < toPosition))))))) {
                        if (!ListenerUtil.mutListener.listen(590)) {
                            {
                                long _loopCounter11 = 0;
                                for (int i = fromPosition; (ListenerUtil.mutListener.listen(589) ? (i >= toPosition) : (ListenerUtil.mutListener.listen(588) ? (i <= toPosition) : (ListenerUtil.mutListener.listen(587) ? (i > toPosition) : (ListenerUtil.mutListener.listen(586) ? (i != toPosition) : (ListenerUtil.mutListener.listen(585) ? (i == toPosition) : (i < toPosition)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                                    if (!ListenerUtil.mutListener.listen(584)) {
                                        Collections.swap(ballotChoiceModelList, i, (ListenerUtil.mutListener.listen(583) ? (i % 1) : (ListenerUtil.mutListener.listen(582) ? (i / 1) : (ListenerUtil.mutListener.listen(581) ? (i * 1) : (ListenerUtil.mutListener.listen(580) ? (i - 1) : (i + 1))))));
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(579)) {
                            {
                                long _loopCounter10 = 0;
                                for (int i = fromPosition; (ListenerUtil.mutListener.listen(578) ? (i >= toPosition) : (ListenerUtil.mutListener.listen(577) ? (i <= toPosition) : (ListenerUtil.mutListener.listen(576) ? (i < toPosition) : (ListenerUtil.mutListener.listen(575) ? (i != toPosition) : (ListenerUtil.mutListener.listen(574) ? (i == toPosition) : (i > toPosition)))))); i--) {
                                    ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                                    if (!ListenerUtil.mutListener.listen(573)) {
                                        Collections.swap(ballotChoiceModelList, i, (ListenerUtil.mutListener.listen(572) ? (i % 1) : (ListenerUtil.mutListener.listen(571) ? (i / 1) : (ListenerUtil.mutListener.listen(570) ? (i * 1) : (ListenerUtil.mutListener.listen(569) ? (i + 1) : (i - 1))))));
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(592)) {
                    listAdapter.notifyItemMoved(fromPosition, toPosition);
                }
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
        if (!ListenerUtil.mutListener.listen(593)) {
            itemTouchHelper.attachToRecyclerView(choiceRecyclerView);
        }
        if (!ListenerUtil.mutListener.listen(594)) {
            this.createChoiceEditText = rootView.findViewById(R.id.create_choice_name);
        }
        if (!ListenerUtil.mutListener.listen(600)) {
            this.createChoiceEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(599)) {
                        if ((ListenerUtil.mutListener.listen(597) ? ((ListenerUtil.mutListener.listen(595) ? (actionId == getResources().getInteger(R.integer.ime_wizard_add_choice) && actionId == EditorInfo.IME_ACTION_NEXT) : (actionId == getResources().getInteger(R.integer.ime_wizard_add_choice) || actionId == EditorInfo.IME_ACTION_NEXT)) && ((ListenerUtil.mutListener.listen(596) ? (event.getAction() == KeyEvent.ACTION_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)))) : ((ListenerUtil.mutListener.listen(595) ? (actionId == getResources().getInteger(R.integer.ime_wizard_add_choice) && actionId == EditorInfo.IME_ACTION_NEXT) : (actionId == getResources().getInteger(R.integer.ime_wizard_add_choice) || actionId == EditorInfo.IME_ACTION_NEXT)) || ((ListenerUtil.mutListener.listen(596) ? (event.getAction() == KeyEvent.ACTION_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) : (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)))))) {
                            if (!ListenerUtil.mutListener.listen(598)) {
                                createChoice();
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(609)) {
            this.createChoiceEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(608)) {
                        if ((ListenerUtil.mutListener.listen(601) ? (s != null || createChoiceButton != null) : (s != null && createChoiceButton != null))) {
                            if (!ListenerUtil.mutListener.listen(607)) {
                                createChoiceButton.setEnabled((ListenerUtil.mutListener.listen(606) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(605) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(604) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(603) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(602) ? (s.length() == 0) : (s.length() > 0)))))));
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(610)) {
            this.createChoiceButton = rootView.findViewById(R.id.create_choice);
        }
        if (!ListenerUtil.mutListener.listen(612)) {
            this.createChoiceButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(611)) {
                        createChoice();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(613)) {
            this.createChoiceButton.setEnabled(false);
        }
        ImageButton addDateButton = rootView.findViewById(R.id.add_date);
        if (!ListenerUtil.mutListener.listen(614)) {
            addDateButton.setOnClickListener(v -> {
                final MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(R.string.select_date).setSelection(originalTimeInUtc != null ? originalTimeInUtc : MaterialDatePicker.todayInUtcMilliseconds()).build();
                datePicker.addOnPositiveButtonClickListener(selection -> {
                    Long date = datePicker.getSelection();
                    if (date != null) {
                        originalTimeInUtc = date;
                        createDateChoice(false);
                    }
                });
                if (isAdded()) {
                    datePicker.show(getParentFragmentManager(), DIALOG_TAG_SELECT_DATE);
                }
            });
        }
        ImageButton addDateTimeButton = rootView.findViewById(R.id.add_time);
        if (!ListenerUtil.mutListener.listen(615)) {
            addDateTimeButton.setOnClickListener(v -> {
                final MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(R.string.select_date).setSelection(originalTimeInUtc != null ? originalTimeInUtc : MaterialDatePicker.todayInUtcMilliseconds()).build();
                datePicker.addOnPositiveButtonClickListener(selection -> {
                    Long date = datePicker.getSelection();
                    if (date != null) {
                        originalTimeInUtc = date;
                        final MaterialTimePicker timePicker = new MaterialTimePicker.Builder().setTitleText(R.string.select_time).setHour(0).setMinute(0).setTimeFormat(DateFormat.is24HourFormat(getContext()) ? CLOCK_24H : CLOCK_12H).build();
                        timePicker.addOnPositiveButtonClickListener(v1 -> {
                            originalTimeInUtc += timePicker.getHour() * DateUtils.HOUR_IN_MILLIS;
                            originalTimeInUtc += timePicker.getMinute() * DateUtils.MINUTE_IN_MILLIS;
                            createDateChoice(true);
                        });
                        if (isAdded()) {
                            timePicker.show(getParentFragmentManager(), DIALOG_TAG_SELECT_TIME);
                        }
                    }
                });
                if (isAdded()) {
                    datePicker.show(getParentFragmentManager(), DIALOG_TAG_SELECT_DATETIME);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(616)) {
            initAdapter();
        }
        return rootView;
    }

    private void createDateChoice(boolean showTime) {
        if (!ListenerUtil.mutListener.listen(623)) {
            if (createChoiceEditText != null) {
                int format = DateUtils.FORMAT_UTC | DateUtils.FORMAT_ABBREV_WEEKDAY | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE;
                if (!ListenerUtil.mutListener.listen(618)) {
                    if (showTime) {
                        if (!ListenerUtil.mutListener.listen(617)) {
                            format |= DateUtils.FORMAT_SHOW_TIME;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(620)) {
                    if (!isSameYear(originalTimeInUtc)) {
                        if (!ListenerUtil.mutListener.listen(619)) {
                            format |= DateUtils.FORMAT_SHOW_YEAR;
                        }
                    }
                }
                String dateString = DateUtils.formatDateTime(getContext(), originalTimeInUtc, format);
                if (!ListenerUtil.mutListener.listen(621)) {
                    createChoiceEditText.setText(dateString);
                }
                if (!ListenerUtil.mutListener.listen(622)) {
                    createChoice();
                }
            }
        }
    }

    private void initAdapter() {
        if (!ListenerUtil.mutListener.listen(628)) {
            if (this.getBallotActivity() != null) {
                if (!ListenerUtil.mutListener.listen(624)) {
                    this.ballotChoiceModelList = this.getBallotActivity().getBallotChoiceModelList();
                }
                if (!ListenerUtil.mutListener.listen(625)) {
                    this.listAdapter = new BallotWizard1Adapter(this.ballotChoiceModelList);
                }
                if (!ListenerUtil.mutListener.listen(626)) {
                    this.listAdapter.setOnChoiceListener(this);
                }
                if (!ListenerUtil.mutListener.listen(627)) {
                    this.choiceRecyclerView.setAdapter(this.listAdapter);
                }
            }
        }
    }

    @Override
    public void onEditClicked(int position) {
        if (!ListenerUtil.mutListener.listen(629)) {
            this.editItemPosition = position;
        }
        TextEntryDialog alertDialog = TextEntryDialog.newInstance(R.string.edit_answer, 0, R.string.ok, R.string.cancel, ballotChoiceModelList.get(position).getName(), InputType.TYPE_CLASS_TEXT, TextEntryDialog.INPUT_FILTER_TYPE_NONE, 5);
        if (!ListenerUtil.mutListener.listen(630)) {
            alertDialog.setTargetFragment(this, 0);
        }
        if (!ListenerUtil.mutListener.listen(631)) {
            alertDialog.show(getFragmentManager(), DIALOG_TAG_EDIT_ANSWER);
        }
    }

    @Override
    public void onYes(String tag, String text) {
        if (!ListenerUtil.mutListener.listen(641)) {
            if (!TestUtil.empty(text)) {
                synchronized (ballotChoiceModelList) {
                    if (!ListenerUtil.mutListener.listen(639)) {
                        if ((ListenerUtil.mutListener.listen(636) ? (editItemPosition >= -1) : (ListenerUtil.mutListener.listen(635) ? (editItemPosition <= -1) : (ListenerUtil.mutListener.listen(634) ? (editItemPosition > -1) : (ListenerUtil.mutListener.listen(633) ? (editItemPosition < -1) : (ListenerUtil.mutListener.listen(632) ? (editItemPosition == -1) : (editItemPosition != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(637)) {
                                ballotChoiceModelList.get(editItemPosition).setName(text);
                            }
                            if (!ListenerUtil.mutListener.listen(638)) {
                                listAdapter.notifyItemChanged(editItemPosition);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(640)) {
                        editItemPosition = -1;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(642)) {
            createChoiceEditText.requestFocus();
        }
    }

    @Override
    public void onNeutral(String tag) {
    }

    @Override
    public void onNo(String tag) {
        if (!ListenerUtil.mutListener.listen(643)) {
            createChoiceEditText.requestFocus();
        }
    }

    @Override
    public void onRemoveClicked(int position) {
        synchronized (ballotChoiceModelList) {
            if (!ListenerUtil.mutListener.listen(644)) {
                ballotChoiceModelList.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(645)) {
                listAdapter.notifyItemRemoved(position);
            }
        }
    }

    /**
     *  Create a new Choice with a Input Alert.
     */
    private void createChoice() {
        if (!ListenerUtil.mutListener.listen(659)) {
            if (TestUtil.required(this.createChoiceEditText.getText())) {
                String text = createChoiceEditText.getText().toString();
                if (!ListenerUtil.mutListener.listen(658)) {
                    if (!TestUtil.empty(text)) {
                        if (!ListenerUtil.mutListener.listen(648)) {
                            createChoice(text.trim(), BallotChoiceModel.Type.Text);
                        }
                        int insertPosition = (ListenerUtil.mutListener.listen(652) ? (this.ballotChoiceModelList.size() % 1) : (ListenerUtil.mutListener.listen(651) ? (this.ballotChoiceModelList.size() / 1) : (ListenerUtil.mutListener.listen(650) ? (this.ballotChoiceModelList.size() * 1) : (ListenerUtil.mutListener.listen(649) ? (this.ballotChoiceModelList.size() + 1) : (this.ballotChoiceModelList.size() - 1)))));
                        if (!ListenerUtil.mutListener.listen(653)) {
                            listAdapter.notifyItemInserted(insertPosition);
                        }
                        if (!ListenerUtil.mutListener.listen(654)) {
                            choiceRecyclerView.smoothScrollToPosition(insertPosition);
                        }
                        if (!ListenerUtil.mutListener.listen(655)) {
                            createChoiceEditText.setText("");
                        }
                        if (!ListenerUtil.mutListener.listen(657)) {
                            createChoiceEditText.post(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(656)) {
                                        createChoiceEditText.requestFocus();
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(647)) {
                            // show keyboard on empty click
                            if (this.getBallotActivity() != null) {
                                if (!ListenerUtil.mutListener.listen(646)) {
                                    EditTextUtil.showSoftKeyboard(this.createChoiceEditText);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void saveUnsavedData() {
        if (!ListenerUtil.mutListener.listen(660)) {
            createChoice();
        }
    }

    private void createChoice(String name, BallotChoiceModel.Type type) {
        BallotChoiceModel choiceModel = new BallotChoiceModel();
        if (!ListenerUtil.mutListener.listen(661)) {
            choiceModel.setName(name);
        }
        if (!ListenerUtil.mutListener.listen(662)) {
            choiceModel.setType(type);
        }
        synchronized (this.ballotChoiceModelList) {
            if (!ListenerUtil.mutListener.listen(663)) {
                this.ballotChoiceModelList.add(choiceModel);
            }
        }
    }

    @Override
    void updateView() {
        if (!ListenerUtil.mutListener.listen(664)) {
            initAdapter();
        }
    }

    private boolean isSameYear(long dateInMillis) {
        Calendar cal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(665)) {
            cal.setTimeInMillis(dateInMillis);
        }
        Calendar cal1 = Calendar.getInstance();
        return (ListenerUtil.mutListener.listen(670) ? (cal1.get(Calendar.YEAR) >= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(669) ? (cal1.get(Calendar.YEAR) <= cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(668) ? (cal1.get(Calendar.YEAR) > cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(667) ? (cal1.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) : (ListenerUtil.mutListener.listen(666) ? (cal1.get(Calendar.YEAR) != cal.get(Calendar.YEAR)) : (cal1.get(Calendar.YEAR) == cal.get(Calendar.YEAR)))))));
    }

    @Override
    public void onMissingTitle() {
    }

    @Override
    public void onPageSelected(int page) {
        if (!ListenerUtil.mutListener.listen(682)) {
            if ((ListenerUtil.mutListener.listen(675) ? (page >= 0) : (ListenerUtil.mutListener.listen(674) ? (page <= 0) : (ListenerUtil.mutListener.listen(673) ? (page > 0) : (ListenerUtil.mutListener.listen(672) ? (page < 0) : (ListenerUtil.mutListener.listen(671) ? (page != 0) : (page == 0))))))) {
                if (!ListenerUtil.mutListener.listen(679)) {
                    this.createChoiceEditText.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(680)) {
                    this.createChoiceEditText.setFocusableInTouchMode(false);
                }
                if (!ListenerUtil.mutListener.listen(681)) {
                    this.createChoiceEditText.setFocusable(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(676)) {
                    this.createChoiceEditText.setFocusableInTouchMode(true);
                }
                if (!ListenerUtil.mutListener.listen(677)) {
                    this.createChoiceEditText.setFocusable(true);
                }
                if (!ListenerUtil.mutListener.listen(678)) {
                    this.createChoiceEditText.requestFocus();
                }
            }
        }
    }
}
