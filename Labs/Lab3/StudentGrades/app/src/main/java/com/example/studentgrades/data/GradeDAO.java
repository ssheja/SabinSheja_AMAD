package com.example.studentgrades.data;

import com.example.studentgrades.model.Grade;

import io.realm.Realm;

public class GradeDAO {

    private Realm realmDb;

    public GradeDAO(){
        this.realmDb = Realm.getDefaultInstance();
    }

    public RealmLiveData<Grade> getAllItems(){
        return new RealmLiveData(realmDb.where(Grade.class).findAllAsync());
    }

    public void insertItem(final String courseName,final String letterGrade){
        final Grade newGrade = new Grade(courseName,letterGrade);
        //start realm write transaction
        realmDb.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //create realm object
                realm.copyToRealm(newGrade);
            }
        });
    }

    public void deleteItem(final String id){
        realmDb.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //finds and deletes the realm object
                realm.where(Grade.class).equalTo("id", id).findAll().deleteAllFromRealm();
            }
        });
    }

    public void deleteAll(){
        //start realm write transaction
        realmDb.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }
    public void close(){
        realmDb.close();
    }
}
