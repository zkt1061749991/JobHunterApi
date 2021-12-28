package com.h3c.jobhunter.util;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Word2VEC {

	private HashMap<String, float[]> wordMap = new HashMap<String, float[]>();

	private int words;
	private int size;
	private int topNSize = 40;//设置相近词个数
	private int layerSize = 200;//向量维度

	/**
	 * 加载模型
	 * 
	 * @param path
	 *            模型的路径
	 * @throws IOException
	 */
	public void loadGoogleModel(String path) throws IOException {
		DataInputStream dis = null;
		BufferedInputStream bis = null;
		double len = 0;
		float vector = 0;
		try {
			bis = new BufferedInputStream(new FileInputStream(path));
			dis = new DataInputStream(bis);
			// //读取词数
			words = Integer.parseInt(readString(dis));
			// //大小
			size = Integer.parseInt(readString(dis));
			String word;
			float[] vectors = null;
			for (int i = 0; i < words; i++) {
				word = readString(dis);
				vectors = new float[size];
				len = 0;
				for (int j = 0; j < size; j++) {
					vector = readFloat(dis);
					len += vector * vector;
					vectors[j] = (float) vector;
				}
				len = Math.sqrt(len);

				for (int j = 0; j < size; j++) {//转换为单位向量
					vectors[j] /= len;
				}

				wordMap.put(word, vectors);//每个词和它的向量
				dis.read();
			}
		} finally {
			bis.close();
			dis.close();
		}
	}

	/**
	 * 加载模型到wordMap
	 * 
	 * @param path
	 *            模型的路径
	 * @throws IOException
	 */
	public void loadJavaModel(String path) throws IOException {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)))) {
			words = dis.readInt();
			size = dis.readInt();

			float vector = 0;

			String key = null;
			float[] value = null;
			for (int i = 0; i < words; i++) {
				double len = 0;
				key = dis.readUTF();
				value = new float[size];
				for (int j = 0; j < size; j++) {
					vector = dis.readFloat();
					len += vector * vector;
					value[j] = vector;
				}

				len = Math.sqrt(len);

				for (int j = 0; j < size; j++) {
					value[j] /= len;
				}
				wordMap.put(key, value);
			}

		}
	}

	private static final int MAX_SIZE = 50;

	/**
	 * 近义词
	 *   ？= 国王 - 男人 + 女人
	 * @return
	 */

	public TreeSet<WordEntry> analogy(String word0, String word1, String word2) {
		float[] wv0 = getWordVector(word0);
		float[] wv1 = getWordVector(word1);
		float[] wv2 = getWordVector(word2);

		if (wv1 == null || wv2 == null || wv0 == null) {
			return null;
		}
		float[] wordVector = new float[size];
		for (int i = 0; i < size; i++) {
			wordVector[i] = wv1[i] - wv0[i] + wv2[i];//计算第4个词向量
		}
		float[] tempVector;
		String name;
		List<WordEntry> wordEntrys = new ArrayList<WordEntry>(topNSize);
		for (Entry<String, float[]> entry : wordMap.entrySet()) {
			name = entry.getKey();
			if (name.equals(word0) || name.equals(word1) || name.equals(word2)) {
				continue;
			}
			float dist = 0;
			tempVector = entry.getValue();
			for (int i = 0; i < wordVector.length; i++) {
				dist += wordVector[i] * tempVector[i];
			}
			insertTopN(name, dist, wordEntrys);
		}
		return new TreeSet<WordEntry>(wordEntrys);
	}

	//将向量积最大的向量添加到wordEntrys
	private void insertTopN(String name, float score, List<WordEntry> wordsEntrys) {
		// TODO Auto-generated method stub
		if (wordsEntrys.size() < topNSize) {
			wordsEntrys.add(new WordEntry(name, score));
			return;
		}
		float min = Float.MAX_VALUE;
		int minOffe = 0;
		for (int i = 0; i < topNSize; i++) {
			WordEntry wordEntry = wordsEntrys.get(i);
			if (min > wordEntry.score) {
				min = wordEntry.score;
				minOffe = i;
			}
		}

		if (score > min) {
			wordsEntrys.set(minOffe, new WordEntry(name, score));
		}

	}
	//找到最相近的40个词节点
	public Set<WordEntry> distance(String queryWord) {

		float[] center = wordMap.get(queryWord);
		if (center == null) {
			return Collections.emptySet();
		}
		center = pint(center);//
		int resultSize = wordMap.size() < topNSize ? wordMap.size() : topNSize;
		TreeSet<WordEntry> result = new TreeSet<WordEntry>();

		double min = Float.MIN_VALUE;
		for (Entry<String, float[]> entry : wordMap.entrySet()) {
			float[] vector = entry.getValue();
			vector = pint(vector);//
			float dist = 0;
			for (int i = 0; i < vector.length; i++) {
				dist += center[i] * vector[i];
			}

			if (resultSize <= result.size()) {
				if (dist > min) {
					result.add(new WordEntry(entry.getKey(), dist));
					result.pollLast();
					min = result.last().score;
				}
			}
			else{
				result.add(new WordEntry(entry.getKey(), dist));
			}
		}
		//result.pollFirst();//排除本身

		return result;
	}

	//找到最相近的40个词节点
	public Set<WordEntry> distance(List<String> words) {
		float[] center = null;
		for (String word : words) {
			center = sum(center, wordMap.get(word));
		}
		center = pint(center);//
		if (center == null) {
			return Collections.emptySet();
		}

		int resultSize = wordMap.size() < topNSize ? wordMap.size() : topNSize;
		TreeSet<WordEntry> result = new TreeSet<WordEntry>();

		double min = Float.MIN_VALUE;
		for (Entry<String, float[]> entry : wordMap.entrySet()) {
			float[] vector = entry.getValue();
			vector = pint(vector);//
			float dist = 0;
			for (int i = 0; i < vector.length; i++) {
				dist += center[i] * vector[i];
			}

			if (resultSize <= result.size()) {
				if (dist > min) {
					result.add(new WordEntry(entry.getKey(), dist));
					result.pollLast();
					min = result.last().score;
				}
			}
			else{
				result.add(new WordEntry(entry.getKey(), dist));
			}

		}


		return result;
	}


	public double cross(float[] vec1,float[] vec2){
		double result = 0 ;
		for(int i = 0;i < vec1.length;i++){
			result += vec1[i] * vec2[i];
		}
		return result;
	}

	public double properWord(String word1,String word2) {
		double pipei = 0;
		float[] center1 = wordMap.get(word1);
		float[] center2 = wordMap.get(word2);

		float dist = 0;
		for (int i = 0; i < center1.length; i++) {
			dist += center1[i] * center2[i];
		}
		pipei = (dist * 10000)/ 10000;
		return (pipei+1)/2;
	}

	//计算句子相似性
	public double properStance(List<String> words1,List<String> words2) {
		double pipei = 0;
		float[] center1 = null;
		float[] center2 = null;
		center1 = new float[layerSize];
		center2 = new float[layerSize];


		for (String word : words1) {
			center1 = sum(center1, wordMap.get(word));
		}
		for (String word : words2) {
			center2 = sum(center2, wordMap.get(word));
		}
		center1 = pint(center1);//
		center2 = pint(center2);//单位向量化*/
		float dist = 0;
		for (int i = 0; i < center1.length; i++) {
			dist += center1[i] * center2[i];
		}
		pipei = (dist * 10000)/ 10000;
         return ( pipei + 1 ) / 2;

	}

	public Set<KeyWord> keyword(List<String> words, int num){
		TreeSet<String> k = new TreeSet<>();
		k.addAll(words);
		TreeSet<KeyWord> keyword = new TreeSet<>();
		List<String> t = new ArrayList<>();

		num =  num  <  words.size() ?  num : words.size();
		double min = Double.MIN_VALUE;

		for(String str : k){
			if(wordMap.get(str) == null){
				continue;
			}
			t.add(str);
	     	double dis = properStance(t,words);

			if(keyword.size() >= num){
				if(dis > min){
					if(keyword.contains(new KeyWord(str,dis))){
						t.clear();
						continue;
					}
					else{
						keyword.add(new KeyWord(str,dis));
						if(keyword.size() > num){
							keyword.pollLast();
						}
						min = keyword.last().getScore();
					}
				}
			}
			else{
				if(keyword.contains(new KeyWord(str,dis))){
					t.clear();
					continue;
				}
				else{
					keyword.add(new KeyWord(str,dis));
				}
			}
			t.clear();
		}
		return keyword;
	}


	//计算文本相似性
	public double properWen(File file1,File file2) throws IOException {
		InputStreamReader isr1 = new InputStreamReader(new FileInputStream(file1), "UTF-8");//文件读入格式
		InputStreamReader isr2 = new InputStreamReader(new FileInputStream(file2), "UTF-8");//文件读入格式
		List<String> wen1 = new ArrayList<>();
		List<String> wen2 = new ArrayList<>();
		try (BufferedReader br1 = new BufferedReader(isr1);BufferedReader br2 = new BufferedReader(isr2)) {
			String temp1 = null;
			String temp2 = null;
			while ((temp1 = br1.readLine()) != null) {
				String[] split = temp1.split(" ");//按空格分词
				for (String string : split) {
					wen1.add(string);
				}
			}
			while ((temp2 = br2.readLine()) != null) {
				String[] split = temp2.split(" ");//按空格分词
				for (String string : split) {
					wen2.add(string);
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properStance(wen1,wen2);
	}

	/**
	* 计算两个词向量的积
	* */

	public float[] vecCross(String word1,String word2){
		float[] result = null;
		float[] word1vec = wordMap.get(word1);
		float[] word2vec = wordMap.get(word2);
		if (word1vec == null || word2vec== null) {
			return null;
		}
		for(int i = 0;i<word1vec.length;i++){
			result[i] = word1vec[i] * word2vec[i];
		}
		return result;
	}

	/**
	 * 计算两个数组的和
	 * */

	private float[] sum(float[] center, float[] fs) {
		// TODO Auto-generated method stub
		if (center == null && fs == null) {
			return null;
		}

		if (fs == null) {
			return center;
		}

		if (center == null) {
			return fs;
		}

		for (int i = 0; i < fs.length; i++) {
			center[i] += fs[i];
		}

		return center;
	}


	/**
	 * 计算两个词向量的和
	 * */
	public float[] vecSum(String word1,String word2){
		float[] result = null;
		float[] word1vec =getWordVector(word1);
		float[] word2vec = getWordVector(word2);
		if (word1vec == null || word2vec== null) {
			return null;
		}
		return sum(word1vec,word2vec);
	}

	public double vecLenth(float[] vec){
		double dis = 0;
		for(int i = 0;i < vec.length;i++){
			dis += vec[i] * vec[i];
		}
		dis = Math.sqrt(dis);
		return dis;
	}

     public float[] pint(float[] vec){
		double dis = vecLenth(vec);
		for(int i = 0 ;i < vec.length; i++){
			vec[i] /= dis;
		}
		return vec;
	}


	/**
	 * 得到词向量
	 * @param word
	 * @return
	 */
	public float[] getWordVector(String word) {
		return wordMap.get(word);
	}

	//计算所有词向量的和
	public float[] allVecSum(){
		float[] result = null;
		for (Entry<String, float[]> entry : wordMap.entrySet()) {
			float[] vector = entry.getValue();
				result = sum(result,vector);
		}
		return result;
	}


	public static float readFloat(InputStream is) throws IOException {
		byte[] bytes = new byte[4];
		is.read(bytes);
		return getFloat(bytes);
	}

	/**
	 * 读取一个float
	 * 
	 * @param b
	 * @return
	 */
	public static float getFloat(byte[] b) {
		int accum = 0;
		accum = accum | (b[0] & 0xff) << 0;
		accum = accum | (b[1] & 0xff) << 8;
		accum = accum | (b[2] & 0xff) << 16;
		accum = accum | (b[3] & 0xff) << 24;
		return Float.intBitsToFloat(accum);
	}

	/**
	 * 读取一个字符串
	 * @param
	 * @return
	 * @throws IOException
	 */
	private static String readString(DataInputStream dis) throws IOException {
		// TODO Auto-generated method stub
		byte[] bytes = new byte[MAX_SIZE];
		byte b = dis.readByte();
		int i = -1;
		StringBuilder sb = new StringBuilder();
		while (b != 32 && b != 10) {
			i++;
			bytes[i] = b;
			b = dis.readByte();
			if (i == 49) {
				sb.append(new String(bytes));
				i = -1;
				bytes = new byte[MAX_SIZE];
			}
		}
		sb.append(new String(bytes, 0, i + 1));
		return sb.toString();
	}

	public int getTopNSize() {
		return topNSize;
	}

	public void setTopNSize(int topNSize) {
		this.topNSize = topNSize;
	}

	public HashMap<String, float[]> getWordMap() {
		return wordMap;
	}

	public int getWords() {
		return words;
	}

	public int getSize() {
		return size;
	}
}
