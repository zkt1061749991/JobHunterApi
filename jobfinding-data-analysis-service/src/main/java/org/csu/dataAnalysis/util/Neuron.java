package com.h3c.jobhunter.util;

public abstract class Neuron implements Comparable<Neuron> {
  public double freq;//频率
  public Neuron parent;//父节点
  public int code;//编码
  // 语料预分类
  public int category = -1;

  @Override
  public int compareTo(Neuron neuron) {
    if (this.category == neuron.category) {
        if (this.freq > neuron.freq) {
        return 1;
        }
        else {
        return -1;
       }
    }
    else if (this.category > neuron.category) {
       return 1;
    }
      else {
        return -1;
    }
  }
}
