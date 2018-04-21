package com.justhire.db;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

public class LoginDBAdapter {

	private static final String DATABASE_NAME = "justhire.db";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase db;
	public static final int NAME_COLUMN = 1;
	static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
			+ "ID" + " integer primary key,"
			+ "NAME  text, CUSTOMER_ID text,AREA_NAME text,PHONE_NO text,USER_NAME text,PWD text,REM text,SL BLOB,PS BLOB); ";


	private final Context context;

	private DBHelper dbHelper;

	public LoginDBAdapter(Context _context) {
		context = _context;
		dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public LoginDBAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	public void insertRow(String name, String customerId, String phoneNo,
			String areaName, String userId, String pwd, boolean remember) {

		byte[] slt = null;
		JHEncryptor encrytor = null;
		try {
			SecureRandom secureRandom = new SecureRandom();
			slt = secureRandom.generateSeed(20);
			encrytor = new JHEncryptor(name, slt, null);
			pwd = encrytor.encrypt(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ContentValues values = new ContentValues();
		values.put("ID", 1);
		values.put("NAME", name);
		values.put("CUSTOMER_ID", customerId);
		values.put("PHONE_NO", phoneNo);
		values.put("AREA_NAME", areaName);
		values.put("USER_NAME", userId);
		values.put("PWD", pwd);
		if (remember) {
			values.put("REM", "Y");
		} else
			values.put("REM", "N");

		values.put("SL", slt);
		values.put("PS", encrytor.getKey());
		db.insert("LOGIN", null, values);

	}

	public int deleteRow(String UserName) {
		String where = "NAME=?";
		int numberOFEntriesDeleted = db.delete("LOGIN", where,
				new String[] { UserName });
		return numberOFEntriesDeleted;
	}

	public JSONObject getRow() {
		JSONObject jo = new JSONObject();
		Cursor cursor = db.query("LOGIN", null, "ID = 1", new String[] {},
				null, null, null);
		if (cursor.getCount() < 1) // User Not Exist
		{
			cursor.close();
			return null;
		}
		cursor.moveToFirst();
		String name = cursor.getString(cursor.getColumnIndex("NAME"));
		String areaName = cursor.getString(cursor.getColumnIndex("AREA_NAME"));
		String phoneNo = cursor.getString(cursor.getColumnIndex("PHONE_NO"));
		String customerId = cursor.getString(cursor
				.getColumnIndex("CUSTOMER_ID"));
		String userName = cursor.getString(cursor.getColumnIndex("USER_NAME"));
		String pwd = cursor.getString(cursor.getColumnIndex("PWD"));
		String remember = cursor.getString(cursor.getColumnIndex("REM"));
		byte[] slt = cursor.getBlob(cursor.getColumnIndex("SL"));
		byte[] ps = cursor.getBlob(cursor.getColumnIndex("PS"));

		cursor.close();

		try {
			JHEncryptor encrytor = new JHEncryptor(name, slt, ps);
			pwd = encrytor.decrypt(pwd);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		try {
			jo.put("name", name);
			jo.put("areaName", areaName);
			jo.put("phoneNo", phoneNo);
			jo.put("customerId", customerId);
			jo.put("userName", userName);
			jo.put("pwd", pwd);
			jo.put("rem", remember);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jo;
	}

	public void updateRow(String name, String customerId, String phoneNo,
			String areaName, String userName, String pwd, boolean remember)

	{
		JHEncryptor encrytor = null;
		byte[] slt = null;
		try {
			SecureRandom secureRandom = new SecureRandom();
			slt = secureRandom.generateSeed(20);
			encrytor = new JHEncryptor(name, slt, null);
			pwd = encrytor.encrypt(pwd);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		ContentValues values = new ContentValues();
		values.put("NAME", name);
		values.put("CUSTOMER_ID", customerId);
		values.put("PHONE_NO", phoneNo);
		values.put("AREA_NAME", areaName);
		values.put("USER_NAME", userName);
		values.put("PWD", pwd);
		if (remember) {
			values.put("REM", "Y");
		} else
			values.put("REM", "N");

		values.put("SL", slt);
		values.put("PS", encrytor.getKey());

		String where = "ID = 1";
		db.update("LOGIN", values, where, new String[] {});
	}

	public void updateRememberMe(String userName, String pwd, boolean remember) {
		ContentValues values = new ContentValues();
		values.put("USER_NAME", userName);
		values.put("PWD", pwd);
		if (remember) {
			values.put("REM", "Y");
		} else
			values.put("REM", "N");
		String where = "ID = 1";
		db.update("LOGIN", values, where, new String[] {});
	}
	
	
	public boolean hasRecord() {
		String[] cols = {"ID"};
		Cursor cursor = db.query("LOGIN",cols, "ID = 1", new String[] {},
				null, null, null);
		if (cursor.getCount() < 1) // User Not Exist
		{
			cursor.close();
			return false;
		}
		cursor.close();
		return true;
		
			}

	private class JHEncryptor {

		private SecretKey keySpec = null;
		private Cipher encipher = null;
		private Cipher decipher = null;

		public JHEncryptor(String pin, byte[] salt, byte[] ps) throws Exception {

			keySpec = generateKey(pin.toCharArray(), salt, ps);
    		// init cipher
			encipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			encipher.init(Cipher.ENCRYPT_MODE, keySpec);
			decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			decipher.init(Cipher.DECRYPT_MODE, keySpec);

		}

		// encrypt the clearText, base64 encode the cipher text and return it.
		public String encrypt(String clearText) throws Exception {

			byte[] cipherText = encipher.doFinal(clearText.getBytes());
			return Base64.encodeToString(cipherText, Base64.DEFAULT);
		}

		// decrypt our resource string back into it's source format.
		public String decrypt(String cipherText) throws Exception {

			byte[] base64TextToDecrypt = Base64.decode(cipherText.getBytes(),
					Base64.DEFAULT);
			byte[] clearText = decipher.doFinal(base64TextToDecrypt);

			return new String(clearText);
		}

		public SecretKey generateKey(char[] passphraseOrPin, byte[] salt,
				byte[] ps) throws NoSuchAlgorithmException,
				InvalidKeySpecException {
			// Number of PBKDF2 hardening rounds to use. Larger values increase
			// computation time. You should select a value that causes
			// computation
			// to take >100ms.
			// final int iterations = 1000;

			// Generate a 256-bit key

			if (ps != null)
				return new SecretKeySpec(ps, "AES");

			final int outputKeyLength = 256;

			SecretKey key = null;

			// if (Build.VERSION.SDK_INT <= 8) {
			// Generate a 256-bit key
			SecureRandom secureRandom = new SecureRandom();
			// Do *not* seed secureRandom! Automatically seeded from system
			// entropy.
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(outputKeyLength, secureRandom);
			key = keyGenerator.generateKey();
			key = new SecretKeySpec((keyGenerator.generateKey()).getEncoded(),
					"AES");
			return key;

			/*
			 * } else { SecretKeyFactory secretKeyFactory = SecretKeyFactory
			 * .getInstance("PBKDF2WithHmacSHA1"); KeySpec keySpec = new
			 * PBEKeySpec(passphraseOrPin, salt, iterations, outputKeyLength);
			 * key = secretKeyFactory.generateSecret(keySpec); } return key;
			 */
		}

		public byte[] getKey() {
			return keySpec.getEncoded();
		}

	}
}
