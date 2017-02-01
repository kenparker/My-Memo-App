package com.maggioni.mymemo.data;

import com.maggioni.mymemo.model.Memo;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MemoStore {
    private List<Memo> memos;

    public MemoStore() {
        super();
        memos = new LinkedList<Memo>();
    }

    public List<Memo> findAll() {
        return memos;
    }

    public void persist(Memo memo) {
        memos.add(memo);
    }

    public void removeAll() {
        memos.clear();
    }
}
