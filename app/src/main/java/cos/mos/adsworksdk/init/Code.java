package cos.mos.adsworksdk.init;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: <p>
 * @Author: Kosmos
 * @Date: 2019.04.18 20:12
 * @Email: KosmoSakura@gmail.com
 */
public interface Code {
    String PKG_NAME = "cos.mos.adsworksdk";
    AtomicBoolean Alive = new AtomicBoolean(false);
}
