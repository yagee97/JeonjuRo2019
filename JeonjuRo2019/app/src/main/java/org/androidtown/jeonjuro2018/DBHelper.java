package org.androidtown.jeonjuro2018;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE INFO(_id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, datatitle TEXT, address TEXT, introcontent TEXT, content TEXT, posx TEXT, posy TEXT, homepage TEXT, accomotype TEXT, storename TEXT, menu TEXT, opentime TEXT, holiday TEXT, img TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String category, String datatitle, String address, String intro, String content, String posx, String posy, String homepage, String accomotype, String storename, String menu, String opentime, String holiday, String img) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO INFO VALUES(null, '" + category + "', " + datatitle + ", '" + address + "', '" + intro + "', '" + content + "', '" + posx + "', '" + posy + "', '" + homepage + "', '" + accomotype + "', '" + storename + "',, '" + menu + "', '" + opentime + "', '" + holiday + "',, '" + img + "');");
        db.close();
    }

    /*public void update(String title, int pagenum, String picture) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE INFO SET picture=" + picture + " WHERE title='" + title + "' AND page='" + pagenum + "';");
        db.close();
    }*/

    /*public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM INFO WHERE title='" + item + "';");
        db.close();
    }*/

    public boolean search(String title,String test) {
        SQLiteDatabase db = getReadableDatabase();
        //pdf 제목이 같은게 있으면 true 반환 없으면 false반환
        Cursor cursor = db.rawQuery("SELECT * FROM INFO WHERE title =='" + title + "' AND testing='" + test +"';", null);

        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public String getResult(String name, int pagenum) { //PDF 제목이랑 페이지수가 같은 경우 picture이름 반환
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Log.i("정보","DB헬퍼 입성");
        Cursor cursor = db.rawQuery("SELECT * FROM INFO WHERE title='" + name + "' AND page='" + pagenum + "';", null);
        Log.i("정보",cursor.toString());

        if(cursor != null) {
            cursor.moveToFirst();
            result += cursor.getString(3);
            cursor.close();
        }

        Log.i("정보",result);
        return result;
    }
}
