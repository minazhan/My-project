package com.example.demo.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

//getHash、getSalt為類別 Hash 自己定義的靜態方法，這些方法都需要在 Hash 類別中自行編寫，並不會自動存在。
//因為 Hash 類別中的所有方法都是靜態方法，因此不需要實例化對象即可使用這些方法，所以不需要用建構子。

public class Hash {
	//產生含鹽雜湊，用指定的密碼和鹽值生成雜湊值。
	public static String getHash(String password,String salt) {
		try {
			//加密演算法: SHA-256
			//其中SHA-256可以換成不同的
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			//加鹽，用 update 方法添加到 MessageDigest 中
			//其中getBytes()是 Java 的內建方法，在 String 類別中。它的作用是將字串轉換為位元組陣列（byte[]）。
			//例如，若字串為 "hello"，則 getBytes() 會返回對應的字節值陣列 [104, 101, 108, 108, 111]。
			md.update(salt.getBytes());//安全性會增加
			//進行加密
			//digest 方法會生成雜湊值並輸出為 byte[] 陣列。
			byte[] bytes=md.digest(password.getBytes());
			//System.out.print(Arrays.toString(bytes));
			//將byte[]透過Base64編碼方便儲存，因為 Base64 能夠表示所有的字節內容。
			//return成功回提早回傳，如果try失敗則會回傳null
			return Base64.getEncoder().encodeToString(bytes);//以字串方式輸出，因為字串比較好存
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//產生鹽，生成一個隨機的鹽值。
	public static String getSalt() {
		//SecureRandom 是一個強加密的隨機數生成器，用來產生更安全的隨機鹽值。
		SecureRandom secureRandom=new SecureRandom();
		byte[] salt=new byte[16];//通常用2的次方
		secureRandom.nextBytes(salt);
		//將生成的 byte[] salt 轉成 Base64 字串，Base64 編碼會將位元組資料編碼成可讀的 ASCII 字元，
		//例如 +、/ 和字母數字。這樣的字串表示不僅可讀，還能避免資料損失。
		return Base64.getEncoder().encodeToString(salt);
	}
	
	
	//產生雜湊，僅用密碼生成雜湊值，沒有鹽。
	//有return產生丟給使用者
	public static String getHash(String password) {
		try {
			//加密演算法: SHA-256，為雜湊演算法
			//其中SHA-256可以換成不同的
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			//進行加密
			byte[] bytes=md.digest(password.getBytes());
			//是 java.util.Arrays 類別中的一個靜態方法，它將 byte[] 轉換為易於閱讀的字串格式
			//可以讓開發者看到 SHA-256 雜湊後的二進位資料（位元組）內容，以便確認程式是否正確運作。
			System.out.print(Arrays.toString(bytes));
			//將byte[]透過Base64編碼方便儲存
			//return成功回提早回傳，如果try失敗則會回傳null
			return Base64.getEncoder().encodeToString(bytes);//以字串方式輸出，因為字串比較好存
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
