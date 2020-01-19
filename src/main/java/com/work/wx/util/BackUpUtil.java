/*
 * work_wx
 * wuhen 2020/1/16.
 * Copyright (c) 2020  jianfengwuhen@126.com All Rights Reserved.
 */

package com.work.wx.util;

public class BackUpUtil {
    private static String RSA = "RSA";

//    static String priKey =
//            "-----BEGIN RSA PRIVATE KEY-----\n" +
//                    "MIICXAIBAAKBgQCtX0eWgh9AD2hPuiNGMSH24ocwB3bpIYruuKk8jUeARb/qfjEx\n" +
//                    "WYNRtdGFs79Pm9L7Sfl29JjuBCpg70iTrF0pIgXd9pjLWdwmeF7IF9e3c5IgjA4I\n" +
//                    "Nml1qe4g/8baXynfhX90vUGdy3UfpFgzT//Danxa/W8jg8Bfj0irgqyIGQIDAQAB\n" +
//                    "AoGAEgqsRHleDyiLTmCscw2B31NLhjAAq9oVvynwUqDRJAQeKKThMaWDCOnG2AcQ\n" +
//                    "jZRFrGjSURK7J2m/jz7XaqaxOv651Nf7xckc8Wr+CVKWcQp0Ekv6kbF6+gbsje9R\n" +
//                    "bP0MUJLtrd4SmNZgOItz7IC+kYuhd/SChDoX447G4HV49AECQQDU59b5PjqYmY81\n" +
//                    "T/bJl6kP2rT55KVVPGOh38EMGy3VZua9HFx4QihxTYOTpmc9cxyfuwMcppyGnm19\n" +
//                    "mgQhdBC5AkEA0HbuJbhXNyxGL2i+XR+jc5ccSqjMR9noXpgG62/BA+6ahOL9ZxBQ\n" +
//                    "1HwIVUs/qQf8cXR71sfmKKu33L0/KjbCYQJAFrYQgY/40jR3SVmZWtHZz/4llg6k\n" +
//                    "8F27xxXGUxNHJV+Pt5ah6pYsGEILiiGTG8P+xq89Wr4PLnER/vcB/8uQyQJAV6or\n" +
//                    "6+DhjGop+bXql+6+JdXeJ+dkQLL6bQ0xm8CbQrQMduWd+sF5vGGMf5Hta3/YQT3i\n" +
//                    "9ieKOoA8Ca/r6CyvAQJBAImOZakR3BndNXJNqQT/a5OPaGTT5D5P1i4GyzixxV7n\n" +
//                    "yZgoxrg+Us6rm30byXCyqF8JC68O/CXaGLlHGPRgiLA=\n" +
//                    "-----END RSA PRIVATE KEY-----";


//    static String priKey_PKCS8 = "-----BEGIN PRIVATE KEY-----\n" +
//            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK1fR5aCH0APaE+6\n" +
//            "I0YxIfbihzAHdukhiu64qTyNR4BFv+p+MTFZg1G10YWzv0+b0vtJ+Xb0mO4EKmDv\n" +
//            "SJOsXSkiBd32mMtZ3CZ4XsgX17dzkiCMDgg2aXWp7iD/xtpfKd+Ff3S9QZ3LdR+k\n" +
//            "WDNP/8NqfFr9byODwF+PSKuCrIgZAgMBAAECgYASCqxEeV4PKItOYKxzDYHfU0uG\n" +
//            "MACr2hW/KfBSoNEkBB4opOExpYMI6cbYBxCNlEWsaNJRErsnab+PPtdqprE6/rnU\n" +
//            "1/vFyRzxav4JUpZxCnQSS/qRsXr6BuyN71Fs/QxQku2t3hKY1mA4i3PsgL6Ri6F3\n" +
//            "9IKEOhfjjsbgdXj0AQJBANTn1vk+OpiZjzVP9smXqQ/atPnkpVU8Y6HfwQwbLdVm\n" +
//            "5r0cXHhCKHFNg5OmZz1zHJ+7AxymnIaebX2aBCF0ELkCQQDQdu4luFc3LEYvaL5d\n" +
//            "H6NzlxxKqMxH2ehemAbrb8ED7pqE4v1nEFDUfAhVSz+pB/xxdHvWx+Yoq7fcvT8q\n" +
//            "NsJhAkAWthCBj/jSNHdJWZla0dnP/iWWDqTwXbvHFcZTE0clX4+3lqHqliwYQguK\n" +
//            "IZMbw/7Grz1avg8ucRH+9wH/y5DJAkBXqivr4OGMain5teqX7r4l1d4n52RAsvpt\n" +
//            "DTGbwJtCtAx25Z36wXm8YYx/ke1rf9hBPeL2J4o6gDwJr+voLK8BAkEAiY5lqRHc\n" +
//            "Gd01ck2pBP9rk49oZNPkPk/WLgbLOLHFXufJmCjGuD5SzqubfRvJcLKoXwkLrw78\n" +
//            "JdoYuUcY9GCIsA==\n" +
//            "-----END PRIVATE KEY-----";


    static String priKey_PKCS8 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK1fR5aCH0APaE+6\n" +
            "I0YxIfbihzAHdukhiu64qTyNR4BFv+p+MTFZg1G10YWzv0+b0vtJ+Xb0mO4EKmDv\n" +
            "SJOsXSkiBd32mMtZ3CZ4XsgX17dzkiCMDgg2aXWp7iD/xtpfKd+Ff3S9QZ3LdR+k\n" +
            "WDNP/8NqfFr9byODwF+PSKuCrIgZAgMBAAECgYASCqxEeV4PKItOYKxzDYHfU0uG\n" +
            "MACr2hW/KfBSoNEkBB4opOExpYMI6cbYBxCNlEWsaNJRErsnab+PPtdqprE6/rnU\n" +
            "1/vFyRzxav4JUpZxCnQSS/qRsXr6BuyN71Fs/QxQku2t3hKY1mA4i3PsgL6Ri6F3\n" +
            "9IKEOhfjjsbgdXj0AQJBANTn1vk+OpiZjzVP9smXqQ/atPnkpVU8Y6HfwQwbLdVm\n" +
            "5r0cXHhCKHFNg5OmZz1zHJ+7AxymnIaebX2aBCF0ELkCQQDQdu4luFc3LEYvaL5d\n" +
            "H6NzlxxKqMxH2ehemAbrb8ED7pqE4v1nEFDUfAhVSz+pB/xxdHvWx+Yoq7fcvT8q\n" +
            "NsJhAkAWthCBj/jSNHdJWZla0dnP/iWWDqTwXbvHFcZTE0clX4+3lqHqliwYQguK\n" +
            "IZMbw/7Grz1avg8ucRH+9wH/y5DJAkBXqivr4OGMain5teqX7r4l1d4n52RAsvpt\n" +
            "DTGbwJtCtAx25Z36wXm8YYx/ke1rf9hBPeL2J4o6gDwJr+voLK8BAkEAiY5lqRHc\n" +
            "Gd01ck2pBP9rk49oZNPkPk/WLgbLOLHFXufJmCjGuD5SzqubfRvJcLKoXwkLrw78\n" +
            "JdoYuUcY9GCIsA==";



    /**
     * 用私钥解密
     *
     * @param encryptedData
     *            经过encryptedData()加密返回的byte数据
     * @param privateKey
     *            私钥
     * @return
     */
    public static String decryptData(String encryptedData)
    {
        try
        {
            return RSAUtil.decryptByPriKey(encryptedData, priKey_PKCS8);
        } catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }







}
