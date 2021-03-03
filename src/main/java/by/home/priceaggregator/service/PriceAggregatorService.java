package by.home.priceaggregator.service;

import by.home.priceaggregator.model.Item;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceAggregatorService {
    public List<Item> getItems() {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        List<Item> items = new ArrayList<>();

        try {
            String searchUrl = "https://mila.by/promo/minus_50_percent/?SORT_TO=90&ELEMENT_CODE=minus_50_percent";
            HtmlPage page = webClient.getPage(searchUrl);
            List<HtmlDivision> itemDivs = page.getByXPath("//div[@class='item product sku']");

            if (CollectionUtils.isEmpty(itemDivs)) {
                throw new IllegalArgumentException("No items found");
            }

            for (HtmlDivision itemDiv : itemDivs) {
                HtmlElement title = itemDiv.getFirstByXPath(".//div[@class='tabloid']/a[@class='name']/span");
                HtmlAnchor price = itemDiv.getFirstByXPath(".//div[@class='tabloid']/div[@class='price-block']/a");



                Item item = new Item(title.asText(),price.asText());

                items.add(item);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return items;
    }
}
