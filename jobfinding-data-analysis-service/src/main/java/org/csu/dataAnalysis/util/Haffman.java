package com.h3c.jobhunter.util;


import java.util.Collection;
import java.util.TreeSet;

/**
 * 构建Haffman编码树
 * 
 * @author ansj
 *
 */
public class Haffman {
  private int layerSize;

  public Haffman(int layerSize) {
    this.layerSize = layerSize;
  }

  private TreeSet<Neuron> set = new TreeSet<>();//树的节点为神经元

  public void make(Collection<Neuron> neurons) {
    set.addAll(neurons);
    while (set.size() > 1) {
      merger();
    }
  }

  private void merger() {//生成哈夫曼树
    HiddenNeuron hn = new HiddenNeuron(layerSize);
    Neuron min1 = set.pollFirst();//min1和min2为目前频率最小的神经元节点
    Neuron min2 = set.pollFirst();
    hn.category = min2.category;
    hn.freq = min1.freq + min2.freq;//父节点的频率为两者和
    min1.parent = hn;
    min2.parent = hn;
    min1.code = 0;//min1为右节点
    min2.code = 1;
    set.add(hn);//添加父节点到set中
  }

}
