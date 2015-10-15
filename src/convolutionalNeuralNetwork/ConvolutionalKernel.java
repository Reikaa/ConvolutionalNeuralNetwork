package convolutionalNeuralNetwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ConvolutionalKernel implements Serializable{

	private static final long serialVersionUID = 5628955368049682368L;
	private int width;
	private int height;
	private double weight[][];
	private double bias;
	private int tag;
	private double[][] change;
	
	public ConvolutionalKernel(int width,int height,double[][] weightSet,double bias,int tag){
		this.bias = bias;
		this.width = width;
		this.height = height;
		weight = new double[height][width];
		for(int i = 0;i < height;i++){
			for(int j = 0;j < width;j++){
				this.weight[i][j] = weightSet[i][j];
				this.change[i][j] = 0;
			}
		}
	    this.tag = tag;
	    change = new double[height][width];
	}
	
	public double getWeight(int row,int colomn){
		return weight[row][colomn];
	}
	
	public double getBias(){
		return bias;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getTag(){
		return this.tag;
	}
	
	public double getChange(int i,int j){
		return change[i][j];
	}
	
	public void setChange(int i,int j,double newChange){
		this.change[i][j] = newChange;
	}
	
	public void setWeight(int i,int j,int newWeight){
		this.weight[i][j] = newWeight;
	}
	
	public void setBias(double newBias){
		this.bias = newBias;
	}
	
	public void writeToDiskCK(String path) throws FileNotFoundException, IOException{
		String fileName = "CK" + this.tag;
		fileName = path + fileName + ".obj";
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(this);
		out.close();
	}
	
	public void readFromDiskCK(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
		String fileName = "CK" + this.tag;
		fileName = path + fileName + ".obj";
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		ConvolutionalKernel newRead = (ConvolutionalKernel)in.readObject();
		this.width = newRead.getWidth();
		this.height = newRead.getHeight();
		this.bias = newRead.getBias();
		this.tag = newRead.getTag();
		for(int i = 0;i < height;i++){
			for(int j = 0;j < width;j++){
				this.weight[i][j] = newRead.getWeight(i, j);
				this.change[i][j] = newRead.getChange(i, j);
			}
		}
		in.close();
	}
	
	
	
	
	
	
}
