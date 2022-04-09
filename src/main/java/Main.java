public class Main {

    private static final UserModule userModule = new UserModule();
    private static final GeneratorModule generatorModule=new GeneratorModule();

    public static void main(String[] args) {

        generatorModule.print();
    }
}
