import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;

public class ProductSorting {
    private ProductFileReader details;
    BufferedWriter bufferedwriter;
    public ProductSorting(String file) throws FileNotFoundException{
        details=new ProductFileReader(file);   
    }

   public void  analyze() throws IOException{
        ProductsData []products=details.getProducts();
        sortByPrice(products);
        sortByRating(products);
        
       
    }

    public void sortByPrice(ProductsData []products){
        for(int count=1;count<=products.length;count++){
            for(int i=0;i<products.length-1;i++){
                if(products[i].getRetailPrice()>products[i+1].getRetailPrice()){
                    ProductsData temp=products[i];
                    products[i]=products[i+1];
                    products[i+1]=temp;
                }
            }
            }
            writeDataToFile(products,"./data/SortedByPrice.csv");
    }

    public void sortByRating(ProductsData []products){
        for(int count=1;count<=products.length;count++){
            for(int i=0;i<products.length-1;i++){
                if(products[i].getProductRating()<products[i+1].getProductRating()){
                    ProductsData temp=products[i];
                    products[i]=products[i+1];
                    products[i+1]=temp;
                }
            }
            }
            writeDataToFile(products,"./data/SortedByRating.csv");
    }

    public void printProducts(ProductsData []products){
        for(ProductsData data:products){
            System.out.println(data);
        }
    }

    public void writeDataToFile(ProductsData []products,String file){
        try{
            bufferedwriter=new BufferedWriter(new FileWriter(file,true));
            bufferedwriter.write(details.header());
            bufferedwriter.newLine();
            for(int index=0;index<products.length;index++){
                bufferedwriter.write(products[index].toString());
                bufferedwriter.newLine();
            }
            bufferedwriter.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}