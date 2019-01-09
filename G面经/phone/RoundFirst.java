第一轮：
给一串synonym pairs 比如
[ (wrong, mistake), (fatality, casualty), (fatality, accident)...]

再给一串queries，对每个query，return whether they are synonym pair by comparing word by word.
比如queries：[(“car fatality bump”, “car accident bump into”),
  (“car casualty”, “car accident”),
  (“dog cat”, “dog cat”)]

(“car fatality bump”, “car accident bump into”) -> 长度就不一样 直接return false
(“car casualty”, “car accident”) -> return true;  car == car，然后casualty 和accident是synonym
(“dog cat”, “dog cat”) -> return true;

//我的做法union find
1.建个HashMap<String, String> parentMap，key是string，value是它的parent
2. 扫synonym pairs两遍：
     第一遍把所有word的parent设成自己，
     第二遍对pair做union
3.建完parentMap之后就可以处理queries了

class SynonymPairsCheck{
  private HashMap<String, String> parentMap = new HashMap<>();

  public void constructMap(ArrayList<String[]>> synonymPairs) {
    for (String[] pair: synonymPairs) {
      parentMap.put(pair[0], pair[0]);
      parentMap.put(pair[1], pair[1]);
    }

    for (String[] pair: synonymPairs) {
      union(pair[0], pair[1]);
    }
  }

  public String find(String x) {
    if (!x.equals(parentMap.get(x))) {
      parentMap.put(x, find(parentMap.get(x)));
    }
    return parentMap.get(x);
  }

  public void union(String x, String y) {
    String parentX = parentMap.get(x); //错！用find(x)
    String parentY = parentMap.get(y); //错！用find(y)
    if (!parentX.equals(parentY)) {
      parentMap.put(x, parentY);
    }
  }

  public List<Boolean> checkQueries (ArrayList<String[]>> queries) {
   List<Boolean> res = new ArrayList<>();
   for (String[] query: queries) {
        String str1 = query[0];
        String str2 = query[1];
        if (str1.length() != str2.length()) return false;
        String[] str1Words = str1.split(" ");
        String[] str2Words = str2.split(" ");
        boolean flag = true;  //
        for (int i = 0; i <  str1Words.length; i++) {
          if (!parentMap.containsKey(str1Words[i]) || !parentMap.containsKey(str2Words[i]))  {
            if (!str1Words[i].equals(str2Words[i])) {
              res.add(False); //想不起来是不是new False还是直接False。。。就先写成了这样
              flag = false;
              break;
            }
          }else {  // current two words are in map, see if they have same parent
            if (!parentMap.get(str1Words[i]).equals(parentMap.get(str2Words[i]))) {
              res.add(Flase);
              flag = false;
              break;
            }
          }
        }
        if (flag) {
          res.add(True);
        }
      }
    return res;
    }
}
