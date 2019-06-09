package opdracht2;

import java.util.List;

/**
 * <code>RijtjesControle</code> bepaald of een lijst van <code>Comparable</code> elementen dalend,
 * stijgend of gelijk is. Lege rijen zijn niet dalend, stijgend of gelijk.
 * 
 * @author Nico Tromp
 */
public interface RijtjesControle {
    <T extends Comparable<T>> boolean isStijgend(List<T> rijtje);

}
