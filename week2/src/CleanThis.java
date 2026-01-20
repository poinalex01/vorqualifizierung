void main() {
    int age = 20;
    boolean withAdult = true;
    boolean VIP = false;
    boolean access = true;

    if (age >= 18) {
        System.out.println("Grünes Band");
    } else if (age >= 16 && age < 18) {
        System.out.println("Gelbes Band");
    } else if (age >= 4 && age < 16 && withAdult) {
        System.out.println("Rotes Band");
    } else {
        System.out.println("Kein Zutritt");
        access = false;
    }

    if (VIP && access) {
        System.out.println("Goldenes Band dazu!");
    } else {
        System.out.println("Kein goldenes Band dazu!");
    }
}

/*
 * > 18 -> GRÜN
 * 16 -18 -> GELB
 * 4-15 -> ROT wenn Begleitperson dabei ist
 * < 4 KEIN EINTRITT
 *
 * VIP zusätzlich GOLD
 * */
