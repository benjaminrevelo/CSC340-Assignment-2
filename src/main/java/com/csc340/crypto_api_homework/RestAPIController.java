package com.csc340.crypto_api_homework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RestAPIController {
    /**
     * Get a list of cryptocurrencies and make them available at our own API endpoint.
     *
     * @return list of cryptos.
     */
    @GetMapping("/crypto")
    public Object getCryptos(){
        try {
            String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jsonListResponse = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jsonListResponse);

            List<Crypto> cryptoList = new ArrayList<>();

            //The response from the abpve API is a Json array, which we loop through
            for(JsonNode rt : root) {
                //Extract relevant info from the above API is from the response and use it for what you want, in this case build a Crypto object
                String name = rt.get("name").asText();
                String price = rt.get("current_price").asText();
                String market_cap = rt.get("market_cap").asText();

                Crypto crypto = new Crypto(name, price, market_cap);
                cryptoList.add(crypto);
            }
            return cryptoList;
        } catch (JsonProcessingException ex){
            Logger.getLogger(RestAPIController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /crypto";

        }
    }
}