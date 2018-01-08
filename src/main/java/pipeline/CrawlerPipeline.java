package pipeline;

import common.WebDriverPool;
import core.model.Product;
import org.apache.logging.log4j.util.Strings;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/4.17:58
 * @Desc: CrawlerPipeline 存储数据
 */
public class CrawlerPipeline implements Pipeline {

    private static Logger logger = Logger.getLogger(String.valueOf(CrawlerPipeline.class));

    private static CrawlerPipeline instance;

    private CrawlerPipeline() {
    }

    public static CrawlerPipeline getInstall() {
        if (instance == null) {                         //Single Checked
            synchronized (WebDriverPool.class) {
                if (instance == null) {                 //Double Checked
                    instance = new CrawlerPipeline();
                }
            }
        }
        return instance;
    }


    @Override
    public void process(ResultItems resultItems, Task task) {
        Product product = resultItems.get("product");
        List<Product> productList = resultItems.get("productList");
        if (product != null && !"".equals(product.getName())) {
            this.dataInput(product);
        }

        if (Objects.nonNull(productList)) {

            for (Product p : productList) {
                this.dataInput(p);
            }
        }
    }


    private void dataInput(Product product) {
        Product productFromDb = Product.dao.findByCode(product.getRef());
        if (productFromDb == null) {
            //没有该商品 添加
            boolean b = product.save();
            if (b) {
                logger.info("成功采集【" + product.getName().trim() + "】");
            }
        } else {
//                //更新价格
            if (!Strings.isBlank(product.getPrice())) {
                productFromDb.setPrice(product.getPrice());
            }
            if (!Strings.isBlank(product.getHkPrice())) {
                productFromDb.setHkPrice(product.getHkPrice());
            }
            if (!Strings.isBlank(product.getEnPrice())) {
                productFromDb.setEnPrice(product.getEnPrice());
            }
            if (!Strings.isBlank(product.getEurPrice())) {
                productFromDb.setEurPrice(product.getEurPrice());
            }
            //updata
            boolean b = productFromDb.update();
            if (b) {
                logger.info("更新【" + productFromDb.getName() + "】成功！！！");
            }
        }
    }
}
