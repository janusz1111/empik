import dto.products.Product;
import dto.products.audio.AudioAlbum;
import dto.products.book.Book;
import dto.products.game.Game;
import dto.products.printed.Poster;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    private final List<Product> productsInCart;

    public ReceiptGenerator(Cart cart) {
        this.productsInCart = cart.getProductsInCart();
    }

    public List<String> createRecipe() {
        List<String> recipe = new ArrayList<>();
        recipe.add(ReceiptLine.getHeader());
        for (Product product : productsInCart) {
            BigDecimal afterDiscountPrice = calculateDiscountForProduct(product);
            ReceiptLine receiptLine = new ReceiptLine(product, afterDiscountPrice);
            recipe.add(receiptLine.toString());
        }
        return recipe;
    }

    public BigDecimal calculateDiscountForProduct(Product product) {

        if (product instanceof AudioAlbum) {
            return discountForAudio(product);
        }

        if (product instanceof Book) {
            return discountForBook(product);
        }

        if (product instanceof Poster) {
            return discountForPoster(product);
        }

        if (product instanceof Game) {
            return discountForGame(product);
        }

        return product.getPrice(); //or exception
    }

    private BigDecimal discountForGame(Product product) {
        Map<String, ArrayList<Game>> gamesGroupedByPublisher = productsInCart.stream()
                .filter(x -> x instanceof Game)
                .map(Game.class::cast) //(x -> (Game)x
                .collect(
                        Collectors.toMap(
                                Game::getPublisher, //1 how create key
                                game -> new ArrayList<>(Collections.singletonList(game)), //Arrays.asList(); //2 how create value
                                (oldVal, newVal) -> {
                                    oldVal.addAll(newVal);
                                    return oldVal;
                                } //3 how resolve conflict (if no specified, just replace old value with new)
                        )
                );

        //alternativeMapForGames();

        Game game = (Game)product;

        ArrayList<Game> gamesOfPublisher = gamesGroupedByPublisher.get(game.getPublisher());
        int postersWithDiscount = gamesOfPublisher.size() / 3;

        //get last x, where x - games with discount
        List<Game> postersForDiscount = gamesOfPublisher.stream()
                .sorted(Comparator.comparing(Game::getPrice))
                .limit(postersWithDiscount)
                .collect(Collectors.toList());

        //is product one of them ? if so, then price should be 0
        if (postersForDiscount.contains(product)) {
            return BigDecimal.ZERO;
        } else {
            return product.getPrice();
        }

    }

    private void alternativeMapForGames() {
        Map<String, ArrayList<Game>> gamesByPublisher = new HashMap<>();
        List<Game> gameList = new ArrayList<>();
        for(Product prod : productsInCart){
            if(prod instanceof Game){
                gameList.add((Game)prod);
            }
        }

        for(Game game : gameList){
            ArrayList<Game> games;
            if(gamesByPublisher.containsKey(game.getPublisher())){
                games = gamesByPublisher.get(gamesByPublisher);
            } else {
                games = new ArrayList<>();
            }
            games.add(game);
            gamesByPublisher.put(game.getPublisher(), games);
        }
    }

    private BigDecimal discountForPoster(Product product) {
        List<Poster> posters = productsInCart.stream()
                .filter(x -> x instanceof Poster)
                .map(x -> (Poster) x)
                .collect(Collectors.toList());

        int posterInCartCounter = posters.size();
        int postersWithDiscount = posterInCartCounter / 3;

        //get last x, where x - posters with discount
        List<Poster> postersForDiscount = posters.stream()
                .sorted(Comparator.comparing(Poster::getPrice))
                .limit(postersWithDiscount)
                .collect(Collectors.toList());

        //is product one of them ? if so, then price should be 1
        if (postersForDiscount.contains(product)) {
            return BigDecimal.ONE;
        } else {
            return product.getPrice();
        }
    }


    private ArrayList<Poster> getXcheapestPosterWithoutStream(ArrayList<Poster> posters, int elementsToReturn){
        Collections.sort(posters, new Comparator<Poster>() {
            @Override
            public int compare(Poster o1, Poster o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
//            Collections.sort(posters, (o1,o2) -> o1.getPrice().compareTo(o2.getPrice()));
        ArrayList<Poster> cheapestPosters = new ArrayList<>();
        for(int i=0; i<elementsToReturn; i++){
            cheapestPosters.add(posters.get(i));
        }
        return cheapestPosters;

    }

    private BigDecimal anotherWayOfFindTheCheapestPoster(Product product, List<Poster> posters) {
        Poster cheapest = null;
        BigDecimal lowestPrice = BigDecimal.valueOf(Long.MAX_VALUE);

        for(Poster poster : posters){
            BigDecimal actualPosterPrice = poster.getPrice();
            if(actualPosterPrice.compareTo(lowestPrice)<0){
                lowestPrice = actualPosterPrice;
                cheapest = poster;
            }
        }
        //najtanszy przypisany do cheapest
        //cheapest
        if(cheapest.equals(product)){
            return BigDecimal.ONE;
        } else {
            return product.getPrice();
        }
    }

    private BigDecimal discountForBook(Product product) {
        Book book = (Book) product;
        int pageCount = book.getPageCounter();
        long percentageDiscount = 0;
        if (pageCount > 400) {
            percentageDiscount = 40;
        } else {
            percentageDiscount = pageCount / 100 * 10;
        }

        //return calculateDiscountPrice(product, get40Percent());
        //return calculateDiscountPrice(product, 40L);
        //return calculateDiscountPrice(product, pageCount > 400 ? 40 : pageCount/100*10);
        return calculateDiscountPrice(product, percentageDiscount);
    }

    private long get40Percent(){
        return 40L;
    }

    private BigDecimal discountForAudio(Product product) {
        AudioAlbum audioAlbum = (AudioAlbum) product;
        LocalDate today = LocalDate.now();
        LocalDate publishmentDate = (audioAlbum).getPublishmentDate();
        long numbersOfYears = ChronoUnit.YEARS.between(publishmentDate, today);
        BigDecimal discountPrice = calculateDiscountPrice(product, numbersOfYears);
        return discountPrice;
    }

    private BigDecimal calculateDiscountPrice(Product product, long percentageOfDiscount) {
        BigDecimal discount = percentage(product.getPrice(), percentageOfDiscount);
        return product.getPrice().subtract(discount);
    }

    private final static BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private BigDecimal percentage(BigDecimal price, long numbersOfYears) {
        return price.multiply(new BigDecimal(numbersOfYears)).divide(ONE_HUNDRED);
    }

}
