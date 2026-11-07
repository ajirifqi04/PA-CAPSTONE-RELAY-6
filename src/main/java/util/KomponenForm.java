package util;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public final class KomponenForm {
    private KomponenForm() {}

    public static <T extends Component> T cari(Container akar, String nama, Class<T> tipe) {
        List<Component> cocok = new ArrayList<>();
        kumpulkan(akar, nama, cocok);
        if (cocok.size() != 1 || !tipe.isInstance(cocok.get(0)))
            throw new IllegalStateException("Komponen Form " + nama + " tidak sesuai. Gunakan pasangan .java dan .form versi yang sama.");
        return tipe.cast(cocok.get(0));
    }

    private static void kumpulkan(Component komponen, String nama, List<Component> cocok) {
        if (nama.equals(komponen.getName())) cocok.add(komponen);
        if (komponen instanceof Container container)
            for (Component anak : container.getComponents()) kumpulkan(anak, nama, cocok);
    }
}
