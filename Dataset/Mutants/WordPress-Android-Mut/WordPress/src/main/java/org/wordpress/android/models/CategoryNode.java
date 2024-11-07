package org.wordpress.android.models;

import android.util.LongSparseArray;
import org.wordpress.android.fluxc.model.TermModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CategoryNode {

    private long mCategoryId;

    private String mName;

    private long mParentId;

    private int mLevel;

    private SortedMap<String, CategoryNode> mChildren = new TreeMap<>(new Comparator<String>() {

        @Override
        public int compare(String s, String s2) {
            if (!ListenerUtil.mutListener.listen(1357)) {
                if (s == null) {
                    if (!ListenerUtil.mutListener.listen(1356)) {
                        if (s2 == null) {
                            return 0;
                        }
                    }
                    return 1;
                } else if (s2 == null) {
                    return -1;
                }
            }
            return s.compareToIgnoreCase(s2);
        }
    });

    public SortedMap<String, CategoryNode> getChildren() {
        return mChildren;
    }

    public void setChildren(SortedMap<String, CategoryNode> children) {
        if (!ListenerUtil.mutListener.listen(1358)) {
            this.mChildren = children;
        }
    }

    public CategoryNode(long categoryId, long parentId, String name) {
        if (!ListenerUtil.mutListener.listen(1359)) {
            this.mCategoryId = categoryId;
        }
        if (!ListenerUtil.mutListener.listen(1360)) {
            this.mParentId = parentId;
        }
        if (!ListenerUtil.mutListener.listen(1361)) {
            this.mName = name;
        }
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        if (!ListenerUtil.mutListener.listen(1362)) {
            this.mCategoryId = categoryId;
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(1363)) {
            this.mName = name;
        }
    }

    public long getParentId() {
        return mParentId;
    }

    public void setParentId(int parentId) {
        if (!ListenerUtil.mutListener.listen(1364)) {
            this.mParentId = parentId;
        }
    }

    public int getLevel() {
        return mLevel;
    }

    public static CategoryNode createCategoryTreeFromList(List<TermModel> categories) {
        CategoryNode rootCategory = new CategoryNode(-1, -1, "");
        // First pass instantiate CategoryNode objects
        LongSparseArray<CategoryNode> categoryMap = new LongSparseArray<>();
        CategoryNode currentRootNode;
        if (!ListenerUtil.mutListener.listen(1366)) {
            {
                long _loopCounter50 = 0;
                for (TermModel category : categories) {
                    ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                    long categoryId = category.getRemoteTermId();
                    long parentId = category.getParentRemoteId();
                    CategoryNode node = new CategoryNode(categoryId, parentId, category.getName());
                    if (!ListenerUtil.mutListener.listen(1365)) {
                        categoryMap.put(categoryId, node);
                    }
                }
            }
        }
        {
            long _loopCounter51 = 0;
            // Second pass associate nodes to form a tree
            for (int i = 0; (ListenerUtil.mutListener.listen(1378) ? (i >= categoryMap.size()) : (ListenerUtil.mutListener.listen(1377) ? (i <= categoryMap.size()) : (ListenerUtil.mutListener.listen(1376) ? (i > categoryMap.size()) : (ListenerUtil.mutListener.listen(1375) ? (i != categoryMap.size()) : (ListenerUtil.mutListener.listen(1374) ? (i == categoryMap.size()) : (i < categoryMap.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                CategoryNode category = categoryMap.valueAt(i);
                if ((ListenerUtil.mutListener.listen(1371) ? (category.getParentId() >= 0) : (ListenerUtil.mutListener.listen(1370) ? (category.getParentId() <= 0) : (ListenerUtil.mutListener.listen(1369) ? (category.getParentId() > 0) : (ListenerUtil.mutListener.listen(1368) ? (category.getParentId() < 0) : (ListenerUtil.mutListener.listen(1367) ? (category.getParentId() != 0) : (category.getParentId() == 0))))))) {
                    // root node
                    currentRootNode = rootCategory;
                } else {
                    currentRootNode = categoryMap.get(category.getParentId(), rootCategory);
                }
                CategoryNode childNode = categoryMap.get(category.getCategoryId());
                if (!ListenerUtil.mutListener.listen(1373)) {
                    if (childNode != null) {
                        if (!ListenerUtil.mutListener.listen(1372)) {
                            currentRootNode.mChildren.put(category.getName(), childNode);
                        }
                    }
                }
            }
        }
        return rootCategory;
    }

    private static void preOrderTreeTraversal(CategoryNode node, int level, ArrayList<CategoryNode> returnValue) {
        if (!ListenerUtil.mutListener.listen(1379)) {
            if (node == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1387)) {
            if ((ListenerUtil.mutListener.listen(1384) ? (node.mParentId >= -1) : (ListenerUtil.mutListener.listen(1383) ? (node.mParentId <= -1) : (ListenerUtil.mutListener.listen(1382) ? (node.mParentId > -1) : (ListenerUtil.mutListener.listen(1381) ? (node.mParentId < -1) : (ListenerUtil.mutListener.listen(1380) ? (node.mParentId == -1) : (node.mParentId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(1385)) {
                    node.mLevel = level;
                }
                if (!ListenerUtil.mutListener.listen(1386)) {
                    returnValue.add(node);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1393)) {
            {
                long _loopCounter52 = 0;
                for (CategoryNode child : node.getChildren().values()) {
                    ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                    if (!ListenerUtil.mutListener.listen(1392)) {
                        preOrderTreeTraversal(child, (ListenerUtil.mutListener.listen(1391) ? (level % 1) : (ListenerUtil.mutListener.listen(1390) ? (level / 1) : (ListenerUtil.mutListener.listen(1389) ? (level * 1) : (ListenerUtil.mutListener.listen(1388) ? (level - 1) : (level + 1))))), returnValue);
                    }
                }
            }
        }
    }

    public static ArrayList<CategoryNode> getSortedListOfCategoriesFromRoot(CategoryNode node) {
        ArrayList<CategoryNode> sortedCategories = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(1394)) {
            preOrderTreeTraversal(node, 0, sortedCategories);
        }
        return sortedCategories;
    }
}
