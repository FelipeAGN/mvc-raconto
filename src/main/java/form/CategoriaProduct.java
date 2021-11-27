package form;

public enum CategoriaProduct {

    SANDWICH(1),
    POSTRE(2),
    BEBIDA(3),
    CAFE(4);

    private int categoriaProductValue;

    CategoriaProduct(int value){
        this.categoriaProductValue = value;
    }

    public int getCategoriaProductValue() {
        return this.categoriaProductValue;
    }

    public static CategoriaProduct fromValue(long value){
        for(CategoriaProduct my: CategoriaProduct.values()){
            if(my.categoriaProductValue == value){
                return my;
            }
        }
        return null;
    }
}
