import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rohan on 10/9/15.
 */
public class ParserJson {

    StringBuilder sb;
    BufferedReader reader;
    ArrayList<AmazonProducts> amazonProductsArrayList;
    AmazonProducts amazonProducts;
    public static String CSV_SEPARATOR=",";


    public String readFile() throws IOException, JSONException {
        sb = new StringBuilder();
        reader = new BufferedReader(new FileReader("/Users/rohan/PycharmProjects/kdd/splits/xab"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
//        ParsingJson(sb.toString());
        return sb.toString();
    }


    public ArrayList<AmazonProducts> ParsingJson(String sb) throws JSONException, FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        amazonProductsArrayList = new ArrayList<AmazonProducts>();
        JSONObject root = new JSONObject(sb);
        JSONArray entryArray = root.getJSONArray("products");

        for (int i = 0; i < entryArray.length(); i++) {
            JSONObject productObj = entryArray.getJSONObject(i);
            amazonProducts = new AmazonProducts();
            amazonProducts.setAsin(productObj.getString("asin"));
            try {
                amazonProducts.setImUrl(productObj.getString("imUrl"));
            } catch (Exception e) {
                amazonProducts.setImUrl("NA");
            }

            try {
                amazonProducts.setDescription(productObj.getString("description"));
            } catch (Exception e) {
                amazonProducts.setDescription("NA");
            }
            try {
                amazonProducts.setTitle(productObj.getString("title"));
            } catch (Exception e) {
                amazonProducts.setTitle("NA");
            }
            try {
                amazonProducts.setPrice(productObj.getDouble("price"));
            } catch (Exception e) {
                amazonProducts.setPrice(0.00);
            }
            try {
                amazonProducts.setSalesRank(productObj.getJSONObject("salesRank").getString("Books"));

            } catch (Exception e) {
                amazonProducts.setSalesRank("NA");
            }
            try {
                JSONObject relations = productObj.getJSONObject("related");
                JSONArray relatedbooks = relations.getJSONArray("also_bought");
                StringBuilder sa = new StringBuilder();
                for (int j = 0; j < relatedbooks.length(); j++) {
                    if(j == 0){
                        sa.append("["+relatedbooks.get(0) +";");
                    }else if (j > 0 && j < relatedbooks.length()-1) {
                        sa.append(relatedbooks.get(j) + ";");
                    }else if(j==relatedbooks.length()-1){
                        sa.append(relatedbooks.get(j)+"]");
                    }
                }
                amazonProducts.setAlsoBought(sa.toString());
            } catch (Exception e) {

                amazonProducts.setAlsoBought("NA");
                System.out.println(e);
            }
            try{
                JSONObject relations = productObj.getJSONObject("related");
                JSONArray buy_after_viewing = relations.getJSONArray("buy_after_viewing");
                StringBuilder sc = new StringBuilder();
                for(int k = 0; k < buy_after_viewing.length(); k++){
                    if(k == 0){
                        sc.append("["+buy_after_viewing.get(0) +";");
                    }else if (k > 0 && k < buy_after_viewing.length()-1) {
                        sc.append(buy_after_viewing.get(k) + ";");
                    }else if(k==buy_after_viewing.length()-1){
                        sc.append(buy_after_viewing.get(k)+"]");
                    }
                }
                amazonProducts.setBuy_after_viewing(sc.toString());
            }catch (Exception e){
                amazonProducts.setBuy_after_viewing("NA");
                System.out.println(e);
            }

            try{
                JSONObject relations = productObj.getJSONObject("related");
                JSONArray alsoViewed = relations.getJSONArray("also_viewed");
                StringBuilder sd = new StringBuilder();
                for(int l = 0; l < alsoViewed.length(); l++){
                    if(l == 0){
                        sd.append("["+alsoViewed.get(0) +";");
                    }else if (l > 0 && l < alsoViewed.length()-1) {
                        sd.append(alsoViewed.get(l) + ";");
                    }else if(l==alsoViewed.length()-1){
                        sd.append(alsoViewed.get(l)+"]");
                    }
                }
                amazonProducts.setAlso_viewed(sd.toString());
            }catch (Exception e){
                amazonProducts.setAlso_viewed("NA");
                System.out.println(e);
            }

            amazonProductsArrayList.add(amazonProducts);
        }
        System.out.println(amazonProductsArrayList.toString());
        return amazonProductsArrayList;

    }

    private void writeToCSV(ArrayList<AmazonProducts> productList)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
            for (AmazonProducts product : productList)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(product.getAsin());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getTitle().replace(","," "));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getImUrl());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getSalesRank());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getPrice());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getAlso_viewed());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getAlsoBought());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(product.getBuy_after_viewing());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }


    public static void main(String[] args) throws IOException, JSONException {
        ParserJson parserJson = new ParserJson();
        String s =  parserJson.readFile();
        List<AmazonProducts> sampleList = parserJson.ParsingJson(s);
        parserJson.writeToCSV((ArrayList<AmazonProducts>) sampleList);

    }

}

