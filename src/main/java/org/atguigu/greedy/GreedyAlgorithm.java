package org.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//贪心算法
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("上海");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas  存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        for (String city : broadcasts.keySet()) {
            allAreas.addAll(broadcasts.get(city));
        }

        //创建ArrayList 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey ，保存在一次遍历过程中能够覆盖最大未覆盖的地区对应的电台的 K
        //如果maxKey 不为空，则会加入到 selects里

        String maxKey = null;

        while (allAreas.size() != 0) {//如果allAreas不为0 ,则表示还没有覆盖到所有的地区
            //※每进行一次while ，需要置空 maxKey
            maxKey = null;

            //遍历broadcasts ,取出对应的K
            for (String key : broadcasts.keySet()) {
                //※每进行一次for
                tempSet.clear();
                //当前这个key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas 集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);
//                if (tempSet.size()>0 && (maxKey==null || tempSet.size()>broadcasts.get(allAreas).size())){
                //如果当前这个集合 包含的未覆盖地区的数量，比maxKey指向的集合未覆盖的地区还多
                //就需要重置maxKey
                // tempSet.size() > broadcasts.get(maxKey).size() 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey 不为空的情况下， 就应该将maxKey加入到selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxkey指向的广播电台覆盖的地区，从allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果的集合是:" + selects);
    }
}
