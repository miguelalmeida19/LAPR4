package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


enum BarcodeFormats {
    UPC ("UPC"),
    CODE_39 ("CODE_39"),
    CODE_128 ("CODE_128"),
    ISBN ("ISBN"),
    SCC_14 ("SCC_14"),
    GS1_DATABAR ("GS1_DATABAR");

    private final String code;
    BarcodeFormats(String code){
        this.code = code;
    }

    public String code() {
        return code;
    }
}

@Embeddable
public class Barcode implements ValueObject, Comparable<Barcode> {

    private static final long serialVersionUID = 1L;

    private String barcode;

    public Barcode(final String Barcode) {
        if (StringPredicates.isNullOrEmpty(Barcode)) {
            throw new IllegalArgumentException(
                    "Barcode should neither be null nor empty");
        }
        this.barcode = Barcode;
    }

    protected Barcode() {
        // for ORM
    }

    public static Barcode valueOf(final String Barcode) {
        final Set<String> set = Arrays
                .stream(BarcodeFormats.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
        AppSettings appSettings = Application.settings();
        if (set.contains(Application.settings().getProductBarcodeFormat())){
            return new Barcode(Barcode);
        }else {
            throw new IllegalArgumentException("That Barcode format is invalid! Check application properties file.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Barcode)) {
            return false;
        }

        final Barcode that = (Barcode) o;
        return this.barcode.equals(that.barcode);
    }

    @Override
    public int hashCode() {
        return this.barcode.hashCode();
    }

    @Override
    public String toString() {
        return this.barcode;
    }

    @Override
    public int compareTo(final Barcode arg0) {
        return barcode.compareTo(arg0.barcode);
    }
}