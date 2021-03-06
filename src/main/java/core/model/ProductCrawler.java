package core.model;


import core.model.base.BaseProductCrawler;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProductCrawler extends BaseProductCrawler<ProductCrawler> {
    public static final ProductCrawler dao = new ProductCrawler().dao();

    private List<ProductCrawler> productCrawlerList;

    public List<ProductCrawler> getProductCrawlerList() {
        return productCrawlerList;
    }

    public void setProductCrawlerList(List<ProductCrawler> productCrawlerList) {
        this.productCrawlerList = productCrawlerList;
    }

    @Override
    public String toString() {
        return "ProductCrawler{} " + super.toString();
    }

    /**
     * 通过商品的code 找到商品
     */
    public ProductCrawler findByCode(String code, String brand) {
        String sql = "SELECT * FROM product_crawler WHERE  ref= ? and brand= ? ";
        ProductCrawler productCrawler = dao.findFirst(sql, code, brand);
        if (productCrawler != null) {
            return productCrawler;
        }
        return null;
    }
}
