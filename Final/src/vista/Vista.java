/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Scanner;
import controlador.Controlador;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 *
 * @author JJSal
 */
public class Vista {
    
    Scanner sc = new Scanner(System.in);
    Controlador c =  new Controlador();
    
    public void runMenu(String menu) throws FileNotFoundException{
        boolean salir=false;
        String opcion;
        this.arranque();
        
        do{
            this.limpiar();
            System.out.printf(menu);
            opcion=sc.nextLine();
            this.limpiar();
            
            switch(opcion){
                case "1"://archivos
                    this.archivo();                    
                    break;
                case "2"://clientes
                    
                    this.clientes();
                    break;
                case "3"://resultados
                    this.resultados();
                    break;
                case "4"://listados
                    this.listados();
                    break;
                case "5"://ventas
                    this.ventas();
                    break;
                case "s"://salir
                case "S":
                    salir=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salir);
        c.terminarPrograma();
        this.limpiar();
        //Salir del programa;
        
        
    }
    
    
    
    
    public void archivo () throws FileNotFoundException{
        
        boolean salira=false;
        do{
            System.out.println("A que formato desea exportar los productos");
            System.out.println("1.-Exportar a productos.col");
            System.out.println("2.-Exportar a productos.html");
            System.out.println("s.-Salir");
            String opcion = sc.nextLine(); 
            switch(opcion){
                case "1"://archivos
                    c.exportarACol();
                    break;
                case "2"://clientes
                    c.exportarAHtml();
                    break;
                case "s"://salir
                case "S":
                    salira=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salira);
        this.limpiar();      
            
    }

    public void clientes(){
        
        boolean salira=false;
        do{
            System.out.println("Que accion desea realizar");
            System.out.println("1.-Dar de Alta");
            System.out.println("2.-Dar de Baja");
            System.out.println("3.-Modificar cliente");
            System.out.println("s.-Salir");
            String opcion = sc.nextLine(); 
            switch(opcion){
                case "1"://archivos
                    this.altaCliente();
                    break;
                case "2"://clientes
                    this.bajaCliente();
                    break;
                case "3":
                    this.modificarCliente();
                    break;
                case "s"://salir
                case "S":
                    salira=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salira);
        this.limpiar();
    }
    
    

    
    public void resultados(){
        
        boolean salira=false;
        do{
            System.out.println("Que informacion desea");
            System.out.println("1.-Importe total de ventas");
            System.out.println("2.-Importe total facturado para un cliente");
            System.out.println("3.-Cálculo del valor total de las existencias del almacén");
            System.out.println("s.-Salir");
            String opcion = sc.nextLine(); 
            switch(opcion){
                case "1":
                    this.totalventas();
                    break;
                case "2":
                    this.importeCliente();
                    break;
                case "3":
                    this.valorAlmacen();
                    break;
                case "s"://salir
                case "S":
                    salira=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salira);        
        this.limpiar();
    }

    public void arranque(){
        c.arranque();
    }
    private void limpiar(){
        for(int i=0; i<2;i++){
            System.out.println("");
        }
    }
    public void errorAlLeer(){
        System.err.printf("%n%nERROR: no se pudo leer el archivo productos");
        System.exit(-1);
    }

    private void listados() {
        
        boolean salira=false;
        do{
            System.out.println("Que Desea Imprimir:");
            System.out.println("1.-Produtos ordenados alfabéticamente");
            System.out.println("2.-Productos oredenados por tipo de IVA y precio");
            System.out.println("3.-Clientes ordenados por apellidos y nombre");
            System.out.println("4.-Clientes ordenados por DNI");
            System.out.println("s.-Salir");
            String opcion = sc.nextLine(); 
            switch(opcion){
                case "1":
                    this.impAlfabeticamente();
                    break;
                case "2":
                    this.impIVAPrecio();
                    break;
                case "3":
                    this.impApeNom();
                    break;
                case "4":
                    this.impDNI();
                    break;
                case "s"://salir
                case "S":
                    salira=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salira);
        this.limpiar();
    }

    private void ventas() {
        
        boolean salira=false;
        do{
            System.out.println("¿Como desea comprar?");
            System.out.println("1.-Venta con ticket");
            System.out.println("2.-Venta con factura");
            System.out.println("s.-Salir");
            String opcion = sc.nextLine(); 
            switch(opcion){
                case "1":
                    this.comprarConTicket();
                    break;
                case "2":
                    this.comprarConFactura();
                    break;
                case "s"://salir
                case "S":
                    salira=true;
                    break;
                default:
                    System.out.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salira);
        this.limpiar();
    }

    private void impAlfabeticamente() {
        String [] productos = c.impAlfabeticamente(); 
        System.out.printf("|%30s|%10s|%10s|%8s|%n","nombre","precio","iva","Unidades");
        for (String productoss : productos) {
            System.out.println(productoss);
        }
        this.limpiar();
        
    }

    private void impIVAPrecio() {
       String [] productos = c.impIVAPrecio(); 
        System.out.printf("|%30s|%10s|%10s|%8s|%n","nombre","precio","iva","Unidades");
        for (String productoss : productos) {
            System.out.println(productoss);
        }
        this.limpiar();
    }

    private void impApeNom() {
        String [] clientess = c.impApeNom();
        System.out.printf("|%9s|%25s|%20s|%40s|%8s|%n","DNI","nombre","apellidos","direccion","estado");
        for (String clientes : clientess) {
            System.out.println(clientes);
        }
        this.limpiar();
        
    }

    private void impDNI() {
        String [] clientess = c.impDNI();
        System.out.printf("|%9s|%25s|%20s|%40s|%8s|%n","DNI","nombre","apellidos","direccion","estado");
        for (String clientes : clientess) {
            System.out.println(clientes);
        }
        this.limpiar();
    }

    private void altaCliente() {
        System.out.println("Introduce el DNI del cliente:");
        String dni=sc.nextLine();
        System.out.println("Introduce el nombre del cliente:");
        String nombre=sc.nextLine();
        System.out.println("Introduce los apellidos del cliente:");
        String apellidos=sc.nextLine();
        System.out.println("Introduce la direccion del cliente del cliente:");
        String direccion=sc.nextLine();
        
        c.altaCliente(dni,nombre,apellidos,direccion);
        this.limpiar();
    }

    private void bajaCliente() {
        System.out.println("Inroduce el DNI del cliente");
        c.bajaCliente(sc.nextLine());
        this.limpiar();
    }

    private void modificarCliente() {
        System.out.println("Inroduce el DNI del cliente");
        String dni=sc.nextLine();
        c.modificarCliente(dni);
        this.limpiar();
    }

    private void comprarConTicket() {
        
        ArrayList <String> compra = new ArrayList<>();
        String producto;
        int cantidad;
        ArrayList <String> listaDeLaCompra;
        listaDeLaCompra = new ArrayList<>();
        ArrayList <Integer> numCantidades = new ArrayList<>();
        System.out.println("Escriba los nombres de los productos a comprar:");
        do{
            System.out.printf("-");
            producto=sc.nextLine();
            if(producto.isEmpty() && listaDeLaCompra.isEmpty()){
                System.out.println("No ha comprado nada");
                return;
            }
            if(c.comprobarDisponibilidad(producto)){
                System.out.println("El producto existe");
                System.out.printf("Cantidad deseada de %s:",producto);
                cantidad=sc.nextInt();
                sc.nextLine();
                if(c.comprobarCantidad(producto,cantidad)){
                    listaDeLaCompra.add(producto);
                    numCantidades.add(cantidad);
                }else{
                    System.out.println("No hay suficientes existencias");
                }
            }else{
                System.out.println("No encontramos su producto");
            }
            if(producto.isEmpty()){
                c.comprar(listaDeLaCompra,numCantidades);
                this.imprimirTicket(listaDeLaCompra,numCantidades);    
                System.out.printf("%nGracias por su compra!%n%n");
            }
        }while(!producto.isEmpty());
    }

    private void comprarConFactura() {
        ArrayList <String> compra = new ArrayList<>();
        String producto;
        int cantidad;
        ArrayList <String> listaDeLaCompra;
        listaDeLaCompra = new ArrayList<>();
        ArrayList <Integer> numCantidades = new ArrayList<>();
        System.out.println("Introduzca DNI del cliente");
        String dni=sc.nextLine();
        if(!(c.comprobarExistenciaDelCliente(dni))){
            System.out.printf("%nEl cliente con DNI %s no esta activo%n%n",dni);
            return;
        }
        System.out.println("Escriba los nombres de los productos a comprar:");
        do{
            System.out.printf("-");
            producto=sc.nextLine();
            if(producto.isEmpty() && listaDeLaCompra.isEmpty()){
                System.out.println("No ha comprado nada");
                return;
            }
            if(c.comprobarDisponibilidad(producto)){
                System.out.println("El producto existe");
                System.out.printf("Cantidad deseada de %s:",producto);
                cantidad=sc.nextInt();
                sc.nextLine();
                if(c.comprobarCantidad(producto,cantidad)){
                    listaDeLaCompra.add(producto);
                    numCantidades.add(cantidad);
                }else{
                    System.out.println("No hay suficientes existencias");
                }
            }else{
                System.out.println("No encontramos su producto");
            }
            if(producto.isEmpty()){
                c.comprar(listaDeLaCompra,numCantidades);
                this.imprimirFactura(listaDeLaCompra,numCantidades,dni);    
                System.out.printf("%nGracias por su compra!%n%n");
            }
        }while(!producto.isEmpty());
    }

    private void imprimirTicket(ArrayList <String> listaDeLaCompra,ArrayList <Integer> numCantidades) {
        LocalDateTime dateTime = LocalDateTime.now();
        int identificador=c.generarNumfac();
        System.out.println("FACTURA SIMPLIFICADA");
        System.out.printf("%d-%d-%d %d:%d%n",dateTime.getDayOfMonth(),dateTime.getDayOfMonth(),dateTime.getDayOfYear(),dateTime.getHour(),dateTime.getMinute());
        System.out.printf("N.FACT.S: %d%n%n",identificador);
        
        System.out.println("*****PVP IVA INCLUIDO*****");
        float suma=0;
        for(int i=0; i<listaDeLaCompra.size();i++){
            if(numCantidades.get(i)==1){
                float precio;
                precio = c.obtenerPercioDe(listaDeLaCompra.get(i));
                System.out.printf("%-30s%10.2f%n",listaDeLaCompra.get(i),precio);
                suma+=precio;
            }else{
                System.out.println(listaDeLaCompra.get(i));
                float precio;
                precio = c.obtenerPercioDe(listaDeLaCompra.get(i));                
                float precioR=numCantidades.get(i)*precio;
                System.out.printf("%5s%4dX%-5.2f%25.2f%n"," ",numCantidades.get(i),precio,precioR);
                suma+=precioR;
            }
        }
        c.generarTicket(suma,identificador);
        for(int i =0;i<40;i++){
            System.out.printf("=");
        }        
        System.out.printf("%n%-30s%10.2f%n","TOTAL A APAGAR",suma);
        for(int i =0;i<40;i++){
            System.out.printf("=");
        }
        System.out.printf("%n%-10s%-10s%-10s","TIPO IVA","BASE","CUOTA");
        float iva4 = c.basePara(listaDeLaCompra,numCantidades,0.04f);
        float iva10 = c.basePara(listaDeLaCompra,numCantidades,0.10f);
        float iva21 = c.basePara(listaDeLaCompra, numCantidades, 0.21f);
        System.out.printf("%n%-10s%-10.2f%-10.2f","4.00%",iva4,(iva4*0.04f));
        System.out.printf("%n%-10s%-10.2f%-10.2f","10.00%",iva10,(iva10*0.10f));
        System.out.printf("%n%-10s%-10.2f%-10.2f","21.00%",iva21,(iva21*0.21f));
    }

    private void valorAlmacen() {
        System.out.printf ("%nEl valor del almacen es %.2f%n%n",c.valorAlmacen());
    }

    private void importeCliente() {
        System.out.println("Indique el DNI del cliente:");
        String dni = sc.nextLine();
        System.out.printf("%nEl importe total del cliente con DNI %s es %.2f%n%n",dni,c.importeCliente(dni));
    }

    private void totalventas() {
        System.out.printf("%nEl importe total es %.2f%n%n",c.totalventas());
    }

    private void imprimirFactura(ArrayList<String> listaDeLaCompra, ArrayList<Integer> numCantidades,String dni) {
        LocalDateTime dateTime = LocalDateTime.now();
        int identificador=c.generarNumfac();
        System.out.println("FACTURA ORDINARIA");
        System.out.printf("%d-%d-%d %d:%d%n",dateTime.getDayOfMonth(),dateTime.getMonthValue(),dateTime.getYear(),dateTime.getHour(),dateTime.getMinute());
        System.out.printf("N.FACT.S: %d%n%n",identificador);
        System.out.println(c.getFormatoFac(dni));
        
        System.out.println("*****PVP IVA INCLUIDO*****");
        float suma=0;
        for(int i=0; i<listaDeLaCompra.size();i++){
            if(numCantidades.get(i)==1){
                float precio;
                precio = c.obtenerPercioDe(listaDeLaCompra.get(i));
                System.out.printf("%-30s%10.2f%n",listaDeLaCompra.get(i),precio);
                suma+=precio;
            }else{
                System.out.println(listaDeLaCompra.get(i));
                float precio;
                precio = c.obtenerPercioDe(listaDeLaCompra.get(i));                
                float precioR=numCantidades.get(i)*precio;
                System.out.printf("%5s%4dX%-5.2f%25.2f%n"," ",numCantidades.get(i),precio,precioR);
                suma+=precioR;
            }
        }
        c.generarFactura(suma,identificador,dni);
        
        for(int i =0;i<40;i++){
            System.out.printf("=");
        }        
        System.out.printf("%n%-30s%10.2f%n","TOTAL A APAGAR",suma);
        for(int i =0;i<40;i++){
            System.out.printf("=");
        }
        System.out.printf("%n%-10s%-10s%-10s","TIPO IVA","BASE","CUOTA");
        float iva4 = c.basePara(listaDeLaCompra,numCantidades,0.04f);
        float iva10 = c.basePara(listaDeLaCompra,numCantidades,0.10f);
        float iva21 = c.basePara(listaDeLaCompra, numCantidades, 0.21f);
        System.out.printf("%n%-10s%-10.2f%-10.2f","4.00%",iva4,(iva4*0.04f));
        System.out.printf("%n%-10s%-10.2f%-10.2f","10.00%",iva10,(iva10*0.10f));
        System.out.printf("%n%-10s%-10.2f%-10.2f","21.00%",iva21,(iva21*0.21f));
    }

}