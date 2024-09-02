package hackerrank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStockPriceTest {

  public static void main(String[] args) throws IOException {

    List<Holding> holdingList =
        getAllHoldings(); //getData("https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/holding");
    System.out.println(holdingList);
    Map<String, Map<String, Double>> holdingQuantitiesByDate = new HashMap<>();
    populateHoldingQuantitiesByDate(holdingList, holdingQuantitiesByDate);

    List<Price> pricingList =
        getAllPrices(); //getData("https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/pricing");
    System.out.println(pricingList.size());
    Map<String, Map<String, Double>> holdingPricesByDate = new HashMap<>();
    populateHoldingPricesByDate(pricingList, holdingPricesByDate);

    System.out.println(calculateNAV("20190101", holdingPricesByDate, holdingQuantitiesByDate));
  }

  public static double calculateNAV(String date,
                                    Map<String, Map<String, Double>> holdingPricesByDate,
                                    Map<String, Map<String, Double>> holdingQuantitiesByDate) {

    Map<String, Double> holdingPrices = holdingPricesByDate.getOrDefault(date, null);
    Map<String, Double> holdingQuantities = holdingQuantitiesByDate.getOrDefault(date, null);
    if (holdingPrices == null || holdingPrices.isEmpty() || holdingQuantities == null || holdingQuantities.isEmpty()) {
      return 0;
    }
    return holdingQuantities.entrySet().stream().map(entry -> {
      String security = entry.getKey();
      Double quantity = entry.getValue();
      Double price = holdingPrices.getOrDefault(security, 0d);
      return quantity * price;
    }).reduce(0d, (a, b) -> a + b);
  }

  private static void populateHoldingPricesByDate(
      List<Price> pricingList, Map<String, Map<String, Double>> holdingPricesByDate) {
    pricingList.forEach(price -> {
      holdingPricesByDate.compute(price.getDate(), (k, v) -> {
        if (v == null) {
          v = new HashMap<>();
        }
        v.compute(price.getSecurity(), (k1, v1) -> v1 == null ?
            price.getPrice() : v1 + price.getPrice());
        return v;
      });
    });
  }

  private static void populateHoldingQuantitiesByDate(List<Holding> holdingList,
                                                      Map<String, Map<String, Double>> holdingQuantitiesByDate) {
    holdingList.forEach(holding -> {
      holdingQuantitiesByDate.compute(holding.getDate(), (k, v) -> {
        if (v == null) {
          v = new HashMap<>();
        }
        v.compute(holding.getSecurity(), (k1, v1) -> v1 == null ?
            holding.getQuantity() : v1 + holding.getQuantity());
        return v;
      });
    });
  }

  public static List<Holding> getAllHoldings() {
    BufferedReader in = null;

    try {
      Gson gson = new Gson();
      URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/holding");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      String readLine = "";
      if (responseCode == HttpURLConnection.HTTP_OK) {
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();
        return gson.fromJson(response.toString(), new TypeToken<List<Holding>>() {
        }.getType());
      }
    } catch (Exception e) {
      System.out.println("Error loading data: " + e.getMessage());
    }
    return Collections.emptyList();
  }

  public static List<Price> getAllPrices() {
    try {
      Gson gson = new Gson();
      URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/pricing");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      String readLine = "";
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();
        return gson.fromJson(response.toString(), new TypeToken<List<Price>>() {
        }.getType());
      }
    } catch (Exception e) {
      System.out.println("Error loading data: " + e.getMessage());
    }
    return Collections.emptyList();
  }

  public static <T> List<T> getData(String urlString) {
    try {
      Gson gson = new Gson();
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      String readLine = "";
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in.readLine()) != null) {
          response.append(readLine);
        }
        in.close();
        return gson.fromJson(response.toString(), new TypeToken<List<T>>() {
        }.getType());
      }
    } catch (Exception e) {
      System.out.println("Error loading data: " + e.getMessage());
    }
    return Collections.emptyList();
  }

  static class PagedData<T> {
    private long totalRecords;
    private long recordsPerPage;
    private int page;
    private String nextPage;
    private List<T> data;
  }

  static class Holding {
    private String date;
    private String security;
    private double quantity;
    private String portfolio;

    public String getDate() {
      return date;
    }

    public String getSecurity() {
      return security;
    }

    public String getPortfolio() {
      return portfolio;
    }

    public double getQuantity() {
      return quantity;
    }
  }

  static class Price {
    private String date;
    private String security;
    private double price;

    public String getDate() {
      return date;
    }

    public String getSecurity() {
      return security;
    }

    public double getPrice() {
      return price;
    }
  }
}
