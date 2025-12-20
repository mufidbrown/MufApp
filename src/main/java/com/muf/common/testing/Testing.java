package com.muf.common.testing;

import com.muf.common.helper.EncryptHelper;
import com.muf.common.helper.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;



public class Testing {

    public static void main(String[] args) {

        try {
            System.out.println(EncryptHelper.encrypt("mufiddev"));
            System.out.println(EncryptHelper.encrypt("bismillah"));
            List<Integer> idList = new ArrayList<Integer>();
            idList.add(1);
            idList.add(8);
            idList.contains(18);
            System.out.println(idList.contains(18));

            System.out.println("===================================================");
            System.out.println(RandomUtil.getNumericRandom(16));
            System.out.println(RandomUtil.getAlphaRandom(16));
            System.out.println(RandomUtil.getAlphaNumericRandom(16));
            System.out.println(RandomUtil.generateUniqueAlhpaNumeric(28));
            System.out.println(RandomUtil.generate32UniqueAlhpaNumeric());
        } catch (Exception e) {
        }

    }
}

