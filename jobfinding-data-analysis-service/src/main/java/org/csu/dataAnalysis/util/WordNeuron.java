package com.h3c.jobhunter.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WordNeuron extends Neuron {
  public String name;
  public double[] syn0 = null; // input->hidden 词向量
  public List<Neuron> neurons = null;// 路径神经元
  public int[] codeArr = null;//路径编码

  public List<Neuron> makeNeurons() {//添加路径神经元并生成哈夫曼编码
    if (neurons != null) {//添加路径神经元
      return neurons;
    }
    Neuron neuron = this;
    neurons = new LinkedList<>();
    while ((neuron = neuron.parent) != null) {
      neurons.add(neuron);
    }

    Collections.reverse(neurons);//路径编码
    codeArr = new int[neurons.size()];

    for (int i = 1; i < neurons.size(); i++) {
      codeArr[i - 1] = neurons.get(i).code;
    }
    codeArr[codeArr.length - 1] = this.code;

    return neurons;
  }

  public WordNeuron(String name, double freq, int layerSize) {
    this.name = name;//词名
    this.freq = freq;//出现频率
    this.syn0 = new double[layerSize];//一个特征长度的数组
    Random random = new Random();
    for (int i = 0; i < syn0.length; i++) {//随机初始化
      syn0[i] = (random.nextDouble() - 0.5) / layerSize;
    }
  }

  /**
   * 用于有监督的创造hoffman tree
   * 
   * @param name
   * @param freq
   * @param layerSize
   */
  public WordNeuron(String name, double freq, int category, int layerSize) {
    this.name = name;
    this.freq = freq;
    this.syn0 = new double[layerSize];
    this.category = category;
    Random random = new Random();
    for (int i = 0; i < syn0.length; i++) {
      syn0[i] = (random.nextDouble() - 0.5) / layerSize;
    }
  }

}