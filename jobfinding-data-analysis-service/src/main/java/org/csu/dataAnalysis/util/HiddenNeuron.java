package com.h3c.jobhunter.util;

public class HiddenNeuron extends Neuron {
    
    public double[] syn1 ; //hidden->out非叶子节点的参数向量
    
    public HiddenNeuron(int layerSize){
        syn1 = new double[layerSize] ;
    }//特征长度的数组
    
}
