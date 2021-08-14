import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProductFileReader{
    BufferedReader bufferedReader;
    private String file;
    String pid,productName,brand,productUrl,discountedPrice;
    float retailPrice;
    float productRating;
    ProductsData []productsList;
    public  ProductFileReader(String file) throws FileNotFoundException{
         this.file=file;
         toReadFile();
    }

    public void toReadFile() throws FileNotFoundException{
       bufferedReader=new BufferedReader(new FileReader(file));
    }

    public int countValue() throws IOException { 
        bufferedReader.readLine();
        int count =0;
        while(bufferedReader.readLine()!=null){
            count++;
        }
        return count;
    }

    public String header() throws IOException{
        toReadFile();
        String header = bufferedReader.readLine();
        return header;
    }

    public ProductsData addDetailsToProduct(String record) throws IOException{
        String[] fields = record.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        ProductsData product = new ProductsData();
        product.setPid(fields[0]);
        product.setProductName(fields[1]);
        if(fields[2].equals("")){
            brand="Null";
            product.setBrand("Null");
        }else{
            product.setBrand(fields[2]);
        }
        product.setProductUrl(fields[3]);
        product.setRetailPrice(floatConverter(fields[4]));
        product.setDiscountedPrice(floatConverter(fields[5]));
        if(fields[6].equals("No rating available"))
        {
            product.setProductRating(0);
        }else{
            product.setProductRating(floatConverter(fields[6]));
        }
        return product;
    }


    public ProductsData[] getProducts() throws IOException{
       
        int count = countValue();
        toReadFile(); // reinitalise buffered reader to move cursor to start point of file
        String header = bufferedReader.readLine();
        String record;
        ProductsData[] productList = new ProductsData[count];
        int index=0;
        while((record = bufferedReader.readLine())!= null){
            ProductsData products = addDetailsToProduct(record);
            productList[index++] = products;
        }
        return productList;
    }
















    public float floatConverter(String value){
        try{
            return Float.parseFloat(value);
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
            return 0.0f;
        }
    }


}
 
 /*   int count;
    ProductsData []product;
    Scanner scanner=new Scanner(System.in);
    String pid,productName,brand,productUrl,discountedPrice,productRating[];
    float []retailPrice;
    BufferedWriter writer;

   public void callValues(){
   // public static void main(String[]args){
    try{
   BufferedReader bufferReader=new BufferedReader(new FileReader("./data/flipkart_product_sample.csv")); 
   writer=new BufferedWriter(new FileWriter("./output.txt",true));
   count=1;
   String header = bufferReader.readLine();

            while(true){
                String line = bufferReader.readLine();
                if(line!=null){
                    count++;
                }else{
                    break;
                }
            } 
            //bufferReader.close();
            System.out.println(count);
    product=new ProductsData[count];
    retailPrice=new float[count];
    productRating=new String[count];
    bufferReader.close();
    bufferReader=new BufferedReader(new FileReader("./data/flipkart_product_sample.csv")); 
           int index=0;
            while(true){
                header = bufferReader.readLine();
                String line = bufferReader.readLine();
                if(line!=null){
                    
                    String []productData = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                //    for(int i=0;i<7;i++){
                //         System.out.println(productData[i]);
                //    }
                    pid=productData[0];
                    productName=productData[1];
                    if(productData[2].equals("")){
                        brand="Null";
                    }else{
                    brand=productData[2];}
                    productUrl=productData[3];

                    try{
                        retailPrice[index]=Float.parseFloat(productData[6]);
                      }catch(NumberFormatException e){
                        retailPrice[index]=0.00f;
                      } 
                   // retailPrice[index]=productData[4];
                    discountedPrice=productData[5];
                    if(productData[6].equals("No rating available"))
                    {
                        productRating[index]="0";
                    }else{
                    productRating[index]=productData[6];}
               ProductsData data=new ProductsData(pid,productName,brand,productUrl,retailPrice[index],discountedPrice,productRating[index]);
                product[index]=data;
              // bufferWriter.write(product[index]);
                index++;
                
             }else{
                 break;
             }
            }
    
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();
    }
   }
}
    public void productBasedOnPrice(){
        //  for(int i=0;i<count;i++){
        //      for(int j=0;j<count-1-i;j++){
        //          if(retailPrice[j] > retailPrice[j+1]){
        //             ProductsData temp=product[j];
        //             product[j]=product[j+1];
        //             product[j+1]=temp;

        //          }
        //      }
        //  }

        for(int i=0;i<count;i++){
            for(int j=0;j<count;j++){
                if(retailPrice[i] > retailPrice[j]){
                ProductsData temp=product[j];
                product[j]=product[i];
                product[i]=temp;
            
            }
        }
    }
     }
     public void display() throws IOException{
       
        try{
        FileOutputStream file=new FileOutputStream("./SortedByPrice.txt");
        for(int i=0;i<10;i++){
          
           // ProductsData data=product[i];
          writer.write(product[i]);
            //bufferWriter.write(data.show());
           // System.out.println(data.show());
        }
     }catch(FileNotFoundException e){
         e.printStackTrace();
     }
    }
}

//",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"*/

