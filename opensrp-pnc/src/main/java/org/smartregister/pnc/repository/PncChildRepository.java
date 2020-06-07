package org.smartregister.pnc.repository;

import android.content.ContentValues;

import androidx.annotation.NonNull;

import net.sqlcipher.database.SQLiteDatabase;

import org.apache.commons.lang3.NotImplementedException;
import org.smartregister.pnc.dao.PncGenericDao;
import org.smartregister.pnc.pojo.PncChild;
import org.smartregister.pnc.utils.PncDbConstants;
import org.smartregister.repository.BaseRepository;

import java.util.List;

public class PncChildRepository extends BaseRepository implements PncGenericDao<PncChild> {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE " + PncDbConstants.Table.PNC_BABY + "("
            + PncDbConstants.Column.PncBaby.MOTHER_BASE_ENTITY_ID + " VARCHAR NOT NULL, "
            + PncDbConstants.Column.PncBaby.APGAR + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.BF_FIRST_HOUR + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.FIRST_CRY + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.COMPLICATIONS + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.DISCHARGED_ALIVE + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.DOB + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.COMPLICATIONS_OTHER + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.FIRST_NAME + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.LAST_NAME + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.GENDER + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.HEIGHT + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.WEIGHT + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.NVP_ADMINISTRATION + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.INTERVENTION_SPECIFY + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.INTERVENTION_REFERRAL_LOCATION + " VARCHAR NULL, "
            + PncDbConstants.Column.PncBaby.CARE_MGT + " VARCHAR NULL )";


    private static final String INDEX_BASE_ENTITY_ID = "CREATE INDEX " + PncDbConstants.Table.PNC_BABY
            + "_" + PncDbConstants.Column.PncBaby.MOTHER_BASE_ENTITY_ID + "_index ON " + PncDbConstants.Table.PNC_BABY +
            "(" + PncDbConstants.Column.PncBaby.MOTHER_BASE_ENTITY_ID + " COLLATE NOCASE);";

    public static void createTable(@NonNull SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_SQL);
        database.execSQL(INDEX_BASE_ENTITY_ID);
    }

    @Override
    public boolean saveOrUpdate(PncChild pncChild) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PncDbConstants.Column.PncBaby.MOTHER_BASE_ENTITY_ID, pncChild.getMotherBaseEntityId());
        contentValues.put(PncDbConstants.Column.PncBaby.APGAR, pncChild.getApgar());
        contentValues.put(PncDbConstants.Column.PncBaby.BF_FIRST_HOUR, pncChild.getBfFirstHour());
        contentValues.put(PncDbConstants.Column.PncBaby.COMPLICATIONS, pncChild.getComplications());
        contentValues.put(PncDbConstants.Column.PncBaby.COMPLICATIONS_OTHER, pncChild.getComplicationsOther());
        contentValues.put(PncDbConstants.Column.PncBaby.DISCHARGED_ALIVE, pncChild.getDischargedAlive());
        contentValues.put(PncDbConstants.Column.PncBaby.DOB, pncChild.getDob());
        contentValues.put(PncDbConstants.Column.PncBaby.FIRST_NAME, pncChild.getFirstName());
        contentValues.put(PncDbConstants.Column.PncBaby.LAST_NAME, pncChild.getLastName());
        contentValues.put(PncDbConstants.Column.PncBaby.HEIGHT, pncChild.getHeight());
        contentValues.put(PncDbConstants.Column.PncBaby.WEIGHT, pncChild.getWeight());
        contentValues.put(PncDbConstants.Column.PncBaby.CARE_MGT, pncChild.getCareMgt());
        contentValues.put(PncDbConstants.Column.PncBaby.NVP_ADMINISTRATION, pncChild.getNvpAdministration());
        contentValues.put(PncDbConstants.Column.PncBaby.GENDER, pncChild.getGender());
        contentValues.put(PncDbConstants.Column.PncBaby.INTERVENTION_REFERRAL_LOCATION, pncChild.getInterventionReferralLocation());
        contentValues.put(PncDbConstants.Column.PncBaby.INTERVENTION_SPECIFY, pncChild.getInterventionSpecify());
        contentValues.put(PncDbConstants.Column.PncBaby.FIRST_CRY, pncChild.getFirstCry());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long rows = sqLiteDatabase.insert(PncDbConstants.Table.PNC_BABY, null, contentValues);
        return rows != -1;
    }

    @Override
    public PncChild findOne(PncChild pncChild) {
        throw new NotImplementedException("");
    }

    @Override
    public boolean delete(PncChild pncChild) {
        throw new NotImplementedException("");
    }

    @Override
    public List<PncChild> findAll() {
        throw new NotImplementedException("");
    }

}