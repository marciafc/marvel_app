package br.com.marcia.marvel.model;

import android.content.Context;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;

import br.com.marcia.marvel.R;

public class Authentication {

    private long mTs;

    private String mHash;

    private String mApiKey;

    private Calendar mCalendar;

    private Context mContext;

    public Authentication(Context context) {
        mCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        this.mContext = context;
        create();
    }

    public void create() {
        this.mTs = mCalendar.getTimeInMillis() / 1000L;
        String publicKey = mContext.getString(R.string.public_key);
        this.mApiKey = publicKey;
        this.mHash = md5(String.valueOf(this.mTs).concat(mContext.getString(R.string.private_key)).concat(publicKey));
    }

    public long getmTs() {
        return this.mTs;
    }

    public String getmHash() {
        return this.mHash;
    }

    public String getmApiKey() {
        return mApiKey;
    }

    /**
     * Aplica a criptografia MD5 no texto informado como parâmetro.
     * @param texto
     * @return o texto criptografado em MD5
     */
    private String md5(final String texto) {
        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(texto.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2) {
                    h.insert(0, "0");
                }
                hexString.append(h);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Log.e(Authentication.class.getName(), e.getMessage());
        }

        return null;
    }
}
