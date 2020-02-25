package com.example.studentgrades.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentgrades.data.GradeDAO;

import io.realm.RealmResults;

public class GradeViewModel extends ViewModel {

    private LiveData<RealmResults<Grade>> gradeList;
    private GradeDAO gradeDAO;

    public GradeViewModel() {
        gradeDAO = new GradeDAO();
        gradeList = gradeDAO.getAllItems();
    }

    public LiveData<RealmResults<Grade>> getItemList() {
        return gradeList;
    }

    public void insertItem(String courseName,String letterGrade){
        gradeDAO.insertItem(courseName,letterGrade);
    }

    public void deleteItem(Grade grade){
        gradeDAO.deleteItem(grade.getId());
    }

    public void deleteAll(){
        gradeDAO.deleteAll();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        gradeDAO.close();
    }
}
