public class AliexpressStringComposer {
    private String name;
    private int pageNumber;
    private StringBuilder base ;
    public AliexpressStringComposer(int pageNumber,String name){
        base = new StringBuilder("https://www.aliexpress.com/wholesale?site=glo&g=y&");
        base.append("SearchText="+productNameCompose(name))
                .append("&page="+pageNumber);
    }
    private String productNameCompose(String productName){
        productName.replaceAll("\\s+","+");
        return productName;
    }

    @Override
    public String toString() {
        return base.toString();
    }
}
