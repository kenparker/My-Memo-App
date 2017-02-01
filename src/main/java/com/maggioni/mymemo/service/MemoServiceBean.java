package com.maggioni.mymemo.service;

import com.maggioni.mymemo.data.MemoStore;
import com.maggioni.mymemo.model.Memo;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MemoServiceBean {

    @EJB
    private MemoStore memoStore;

    public List<Memo> getAllMemos() {
        return memoStore.findAll();
    }

    public void addMemo(Memo memo) {
        Memo newMemo = new Memo();
        newMemo.setDescription(memo.getDescription());
        newMemo.setCreated(new Date());
        memoStore.persist(newMemo);
    }

    public void resetMemos() {
        memoStore.removeAll();
    }
}
