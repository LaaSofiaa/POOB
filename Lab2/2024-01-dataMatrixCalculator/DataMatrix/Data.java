
/**
 * Represents a simple data: boolean ('TRUE', 'FALSE'), numerical (integer or real), character (any character, if it is a digit it is considered numeric)
 *
 * @author ECI-2024-01
 */
public class Data{
    
    private char type;
    private String cadena;
    private char moduloc;
    private float moduloi;
    private boolean modulob;
    
    /**
     * Constructs a new Data given its value, If it is not possible, it is assumed FALSE
     */
    public Data(String s){
        cadena = s;
        type = 'f';
        moduloc = 'f';
        moduloi = 0;
        modulob = false;
    }

     /**
     * Add the specified data with this data
     */   
    // boolean + boolean. + is OR
    // numerical + numerical. + is +
    // character + character. + is the largest character
    // boolean + numerical. Transform the number to boolean. If 0, false. True otherwise.
    // boolean + character. Transform the boolean to character. TRUE is T and FALSE is F.
    // character + numerical. Transform the character to ACSII number. ASCII number + numerical
    // + is commutative
    public Data add(Data b){
        Data resp = new Data("f");
        this.toString();
        b.toString();
        switch (this.type){
            //caso para cuando el tipo de dato inicial es numerico
            case 'n':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //suma entre numerico y numerico
                    case 'n':
                        resp.cadena = (b.moduloi + this.moduloi) + "";
                        resp.moduloi = b.moduloi + this.moduloi;
                        resp.type = 'n';
                        break;
                    //caso para cuando el tipo de dato b es booleano
                    //suma entre numerico y booleano
                    case 'b':
                        if (this.moduloi != 0){
                            resp.cadena = (true || b.modulob) + "";
                            resp.modulob = b.modulob || true;             
                        }else{
                            resp.cadena = (false || b.modulob) + "";
                            resp.modulob = b.modulob || false;
                        }
                        resp.type = 'b';  
                        break;
                    //caso para cuando el tipo de dato b es caracter
                    //suma entre numerico y caracter
                    case 'c':
                        float numAscii = b.moduloc;
                        resp.cadena = (numAscii + this.moduloi) + "";
                        resp.moduloi = numAscii + this.moduloi;
                        resp.type = 'n';
                        break;
                }
                break;
            //caso para cuando el tipo de dato inicial es booleano
            case 'b':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //suma entre booleano y numerico
                    case 'n':
                        if (b.moduloi != 0){
                            resp.cadena = (true || this.modulob) + "";
                            resp.modulob = this.modulob || true;             
                        }else{
                            resp.cadena = (false || this.modulob) + "";
                            resp.modulob = this.modulob || false;
                        }
                        resp.type = 'b';
                        break;
                    //caso para cuando el tipo de dato b es numerico
                    //suma entre booleano y booleano
                    case 'b':
                        resp.cadena = (this.modulob || b.modulob) + "";
                        resp.modulob = this.modulob || b.modulob;
                        resp.type = 'b';
                        break;
                    //caso para cuando el tipo de dato b es numerico
                    //suma entre booleano y caracter
                    case 'c':
                        char modB = 'T';
                        if (this.modulob == false){
                            modB = 'F';
                        }
                        if (modB < b.moduloc){
                            resp.cadena = b.moduloc + "";
                            resp.moduloc = b.moduloc;
                        }else{
                            resp.cadena = modB + "";
                            resp.moduloc = modB;
                        }
                        resp.type = 'c';
                        break;
                }
                break;
            //caso para cuando el tipo de dato inicial es caracter
            case 'c':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //suma entre caracter y numerico
                    case 'n':
                        float numAscii = this.moduloc;
                        resp.cadena = (numAscii + b.moduloi) + "";
                        resp.moduloi = numAscii + b.moduloi;
                        resp.type = 'n';
                        break;
                    //caso para cuando el tipo de dato b es booleano
                    //suma entre caracter y booleano
                    case 'b':
                        char modB = 'T';
                        if (b.modulob == false){
                            modB = 'F';
                        }
                        if (modB < this.moduloc){
                            resp.cadena = this.moduloc + "";
                            resp.moduloc = this.moduloc;
                        }else{
                            resp.cadena = modB + "";
                            resp.moduloc = modB;
                        }
                        resp.type = 'c';
                        break;
                    //caso para cuando el tipo de dato b es caracter
                    //suma entre caracter y caracter
                    case 'c':
                        if (b.moduloc < this.moduloc){
                            resp.cadena = this.moduloc + "";
                            resp.moduloc = this.moduloc;
                        }else{
                            resp.cadena = b.moduloc + "";
                            resp.moduloc = b.moduloc;
                        }
                        resp.type = 'c';
                        break;
                }
                break;
        }
        return resp;
    }
    
     /**
     * Substract the specified data with this data
     */   
    // boolean - boolean. - is AND
    // numerical - numerical. + is -
    // character - character. - is the smaller character
    // boolean - numerical. Transform the number to boolean. If 0, true. False otherwise.
    // boolean - character. Transform the boolean to character. TRUE is T and FALSE is F.
    // character - numerical. Transform the character to ACSII number. ASCII number - numerical
    // + is commutative
    public Data sub(Data b){
        Data resp = new Data("f");
        this.toString();
        b.toString();
        switch (this.type){
            //caso para cuando el tipo de dato inicial es numerico
            case 'n':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //resta entre numerico y numerico
                    case 'n':
                        resp.cadena = (this.moduloi - b.moduloi) + "";
                        resp.moduloi = this.moduloi - b.moduloi;
                        resp.type = 'n';
                        break;
                    //caso para cuando el tipo de dato b es booleano
                    //resta entre numerico y booleano
                    case 'b':
                        if (this.moduloi != 0){
                            resp.cadena = (true && b.modulob) + "";
                            resp.modulob = true && b.modulob;             
                        }else{
                            resp.cadena = (false && b.modulob) + "";
                            resp.modulob = false && b.modulob;
                        }
                        resp.type = 'b';  
                        break;
                    //caso para cuando el tipo de dato b es caracter
                    //resta entre numerico y caracter
                    case 'c':
                        float numAscii = b.moduloc;
                        resp.cadena = (this.moduloi - numAscii) + "";
                        resp.moduloi = this.moduloi - numAscii;
                        resp.type = 'n';
                        break;
                }
                break;
            //caso para cuando el tipo de dato inicial es booleano
            case 'b':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //resta entre booleano y numerico
                    case 'n':
                        if (b.moduloi != 0){
                            resp.cadena = (this.modulob && true) + "";
                            resp.modulob = this.modulob && true;             
                        }else{
                            resp.cadena = (this.modulob && false) + "";
                            resp.modulob = this.modulob && false;
                        }
                        resp.type = 'b';
                        break;
                    //caso para cuando el tipo de dato b es numerico
                    //resta entre booleano y booleano
                    case 'b':
                        resp.cadena = (this.modulob && b.modulob) + "";
                        resp.modulob = this.modulob && b.modulob;
                        resp.type = 'b';
                        break;
                    //caso para cuando el tipo de dato b es numerico
                    //resta entre booleano y caracter
                    case 'c':
                        char modB = 'T';
                        if (this.modulob == false){
                            modB = 'F';
                        }
                        if (modB > b.moduloc){
                            resp.cadena = b.moduloc + "";
                            resp.moduloc = b.moduloc;
                        }else{
                            resp.cadena = modB + "";
                            resp.moduloc = modB;
                        }
                        resp.type = 'c';
                        break;
                }
                break;
            //caso para cuando el tipo de dato inicial es caracter
            case 'c':
                switch (b.type){
                    //caso para cuando el tipo de dato b es numerico
                    //resta entre caracter y numerico
                    case 'n':
                        float numAscii = this.moduloc;
                        resp.cadena = (numAscii - b.moduloi) + "";
                        resp.moduloi = numAscii - b.moduloi;
                        resp.type = 'n';
                        break;
                    //caso para cuando el tipo de dato b es booleano
                    //resta entre caracter y booleano
                    case 'b':
                        char modB = 'T';
                        if (b.modulob == false){
                            modB = 'F';
                        }
                        if (modB > this.moduloc){
                            resp.cadena = this.moduloc + "";
                            resp.moduloc = this.moduloc;
                        }else{
                            resp.cadena = modB + "";
                            resp.moduloc = modB;
                        }
                        resp.type = 'c';
                        break;
                    //caso para cuando el tipo de dato b es caracter
                    //resta entre caracter y caracter
                    case 'c':
                        if (b.moduloc > this.moduloc){
                            resp.cadena = this.moduloc + "";
                            resp.moduloc = this.moduloc;
                        }else{
                            resp.cadena = b.moduloc + "";
                            resp.moduloc = b.moduloc;
                        }
                        resp.type = 'c';
                        break;
                }
                break;
        }
        return resp;
    }
    
    /**
     * Returns the data type
     * @returns 'b', 'n', or 'c'
     */
    public char type(){
        this.toString();
        return this.type;
    }
    
    @Override
    /**
     * Return the string representation of the data, not the original string
     */
    public String toString () {
       String respuesta = "";
       this.string();
       try {
            float newS = Float.parseFloat(cadena);
            this.moduloi = newS;
            int newI = (int) newS;
            respuesta = respuesta + newS;
            this.type = 'n';
            return respuesta;
        } catch (NumberFormatException e1) {
            try {
                float newS = Float.parseFloat(cadena);
                this.type = 'n';
                this.moduloi = newS;
                return respuesta + newS;
            } catch (NumberFormatException e2) {
                if (cadena.toLowerCase().equals("true")){
                    this.type = 'b';
                    this.modulob = true;
                    return "TRUE";
                }else if (cadena.toLowerCase().equals("false")){
                    this.type = 'b';
                    this.modulob = false;
                    return "FALSE";
                }else if (cadena.length() > 1){
                    this.type = 'b';
                    this.modulob = false;
                    return "FALSE";
                }else{
                    this.type = 'c';
                    char newC = cadena.charAt(0);
                    this.moduloc = newC;
                    return cadena;
                }
            }
        }
    }   
    
     /**
     * Return the string used to create the Data without leading or trailing blanks
     */   
    public String string() {
        String newString = "";
        for (int i = 0; i < cadena.length(); i++){
            if (cadena.charAt(i) != ' '){
                newString += cadena.charAt(i);
            }
        }
        cadena = newString;
        if (newString == ""){
            return " ";
        }else {
            return newString;
        }
    }  
    
    /**
     * retorna si 2 Datas tienen el mismo valor.
     * @return boolean
     */
    public boolean equals(Data obj2){
        if(this.toString().equals(obj2.toString())){
            return true;
        }else{
            return false;
        }
    }
}
