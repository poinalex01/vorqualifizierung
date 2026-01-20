/*
Schreibe ein Programm, mit dem du die Häufigkeit der Werte in einem Array als Histogramm anzeigst.
Ein Histogramm ist eine grafische Aufarbeitung von Daten, in der dargestellt wird, wie oft ein Wert vorkommt.

Gehe bei der Umsetzung der Aufgabe wie folgt vor:

Erzeuge ein int-Array der Länge 100 und befülle es mit Zufallszahlen im Bereich von 0 bis 9.

Lege ein zweites Array an, in dem du die Häufigkeiten der Werte zählst.
Die Häufigkeit des Wertes 0 wird im Array-Element mit dem Index 0 gezählt.
Die Häufigkeit des Wertes 1 wird im Array-Element mit dem Index 1 gezählt...

Hinweis: Ein int Array bekommt bei Initialisieren für jedes Feld den Wert 0 zugewiesen.

Nachdem du die Häufigkeiten aller Werte erfasst hast, sollst du das Ergebnis auf der Konsole ausgeben.

Bsp.:

    0 *********
    1 *********
    2 ************
    3 *********
    4 **************
    5 ***********
    6 *********
    7 ******
    8 ******
    9 ***************
Im obigen Beispiel sieht man, dass der Wert 0 neun-mal vorkommt. Der Wert 7 kommt sechs-mal vor.
 */

void main() {
    Random random = new Random();
    int[] randomNumbers = random.ints(100, 0, 10).toArray();
    int[] histogram = new int[10];

    for (int i = 0; i < histogram.length; i++) {
        for (int randomNumber : randomNumbers) {
            if (randomNumber == i) {
                histogram[i]++;
            }
        }
    }

    for (int i = 0; i < histogram.length; i++) {
        System.out.println(i + " " + "*".repeat(histogram[i]));
    }
}