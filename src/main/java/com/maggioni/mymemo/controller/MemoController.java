package com.maggioni.mymemo.controller;

import com.maggioni.mymemo.model.Memo;
import com.maggioni.mymemo.service.MemoServiceBean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@SessionScoped
@ManagedBean(name = "memoController")
public class MemoController {
    private Memo memo;

    @EJB
    private MemoServiceBean memoService;

    public MemoController() {
        memo = new Memo();
    }

    public Memo getMemo() {
        return memo;
    }

    public void setMemo(Memo memo) {
        this.memo = memo;
    }

    public void doAdd(ActionEvent event) {
        memoService.addMemo(memo);
        memo.setDescription("");
    }

    public void doReset(ActionEvent event) {
        memoService.resetMemos();
    }

    public List<Memo> getMemos() {
        return memoService.getAllMemos();
    }
}
