import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.Window.limit;

public class Game extends Product {
    String publisher;

    public Game(long id, String name, BigDecimal price) {
        super( id, name, price );

        List<Game> game = Cart.productsInCart.stream()
                .filter(x -> (instanceof Game)
                .map(x ->(Game) x)
                 .collect( Collectors.toList() );

        List<Game> game = Cart.productsInCart.stream()
                .sorted(Collections.(Game ::getPrice))
                .limit( 3 )
                .collect( Collectors.toList());


      
      //game.sort( price );
    }

    public BigDecimal gameDiscount(List<Game> productsInCart, int ile) {
        if (ile >= 3) {    // jeżeli jest więcej niz 3 to najtańsza gratis
            for ( int i =0;i<ile; i++){
                if ( price
            }
            }
            if (productsInCart( price ))


                return gameDiscount( Cart.productsInCart );
        }
        public BigDecimal SalePrice (BigDecimal discount){
            BigDecimal salePrice = BigDecimal.ZERO;


            return salePrice;
        }
    }
