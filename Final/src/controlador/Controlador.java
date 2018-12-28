/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import modelo.Cliente;
import modelo.Producto;
import modelo.Factura;
import modelo.OrdenarClientePorApellidos;
import modelo.OrdenarClientePorDni;
import modelo.OrdenarProductoAlfabeticamente;
import modelo.OrdenarProductoPorIvaPrecio;
import modelo.Supermercado;
import modelo.Ticket;
/**
 *
 * @author JJSal
 */
public class Controlador {
    Supermercado su = new Supermercado();
    
    
    public void exportarACol() throws FileNotFoundException {
        if(su.getColeccionDeProductos()==null){
            System.out.println("Error no se pudo imprimir los productos");
            return;
        }
        try (PrintWriter pw = new PrintWriter (su.getRuta()+File.separator+"productos.col")) {
            String[]vector= new String [su.getColeccionDeProductos().size()];
            Iterator iter = su.getColeccionDeProductos().iterator();
            pw.printf("|%30s|%10s|%10s|%8s|%n","nombre","precio","iva","Unidades");
            for(int i = 0;iter.hasNext();i++){
                Producto pr = (Producto) iter.next();
                vector[i] = pr.ImprimeProducto();
                pw.println(vector[i]);
            }
            pw.close();
        }catch(IOException ex){
            System.out.println("ERROR: NO SE PUDO ESCRIBIR EL ARCHIVO");
        }
    }
    public void exportarAHtml() throws FileNotFoundException {
        if(su.getColeccionDeProductos()==null){
            System.out.println("Error no se pudo imprimir los productos");
            return;
        }
        try (PrintWriter pw = new PrintWriter (su.getRuta()+File.separator+"productos.html")) {
            pw.printf("<!DOCTYPE html><HTML>%n<HEAD><meta charset=\"UTF-8\"><H1></HEAD>%n");
            pw.printf("<TABLE BORDER=1>%n");
            pw.printf(String.format("<TR>"
            +"<TD>%s</TD>" //nombre
            +"<TD>%s</TD>" //precio
            +"<TD>%s</TD>" //iva
            +"<TD>%s</TD>" //unidades
            +"</TR>",
            "Nombre",
            "Precio",
            "Iva",
            "Unidades"));
            su.getColeccionDeProductos().forEach((p) -> {
                pw.printf("%s%n",p.imprimeHTML());
            });
            pw.printf("</TABLE>%n</BODY>%n</HTML>");
            pw.close();
            System.out.printf("%n%nSe ha creado una tabla con %d registros %n%n",su.getColeccionDeProductos().size());
            System.out.println("Ruta del archivo: " + su.getRuta()+File.separator+"productos.html");
            System.out.println("");
        }catch(IOException ex){
            System.out.println("ERROR: NO SE PUDO ESCRIBIR EL ARCHIVO");
        }
        
    }

    public void altaCliente(String dni,String nombre,String apellidos,String direccion) {
        Cliente e = new Cliente(dni,nombre,apellidos,direccion,"activo");
        ArrayList <Cliente> coleccion = su.getColeccionDeClientes();
        coleccion.add(e);
        su.setColeccionDeClientes(coleccion);
    }

    public void bajaCliente(String dni) {
        if(su.getColeccionDeClientes()==null){
            System.out.println("Error no se pudo imprimir los productos");
            return;
        }
        ArrayList <Cliente> coleccion = su.getColeccionDeClientes();
        coleccion.stream().filter((c) -> (c.dniIguales(dni))).forEachOrdered((c) -> {
            c.darBaja();
        });
    }

    public void modificarCliente(String dni) {
        if(su.getColeccionDeClientes()==null){
            System.out.println("Error no se pudo imprimir los productos");
            return;
        }
        ArrayList <Cliente> coleccion = su.getColeccionDeClientes();
        coleccion.stream().filter((c) -> (c.dniIguales(dni))).forEachOrdered((c) -> {
            c.cambiarEstado();
        });
    }

    public float totalventas() {
        float suma=0f;
        ArrayList <Cliente> coleccionDeClientes =  su.getColeccionDeClientes();
        suma = coleccionDeClientes.stream().map((c) -> this.importeCliente(c.getDni())).reduce(suma, (accumulator, _item) -> accumulator + _item);
        ArrayList<Ticket> coleccionDeTickets = su.getColeccionDeTickets();
        suma = coleccionDeTickets.stream().map((t) -> t.getImporteTotal()).reduce(suma, (accumulator, _item) -> accumulator + _item);
        return suma;
    }

    public float importeCliente(String dni) {
        float suma=0f;
        ArrayList <Cliente> coleccionDeClientes =  su.getColeccionDeClientes();
        for(Cliente c : coleccionDeClientes){
            if(c.getDni().equals(dni)){
                    if(!(c.getColeccionDeFacturas().isEmpty())){
                        for(int i=0;i<c.getColeccionDeFacturas().size();i++){
                        suma+=c.getColeccionDeFacturas().get(i).getImporteTotal();
                    }
                    return suma;
                }
            }
        }
        return 0;
    }

    public float valorAlmacen() {
        ArrayList <Producto> coleccionDeProductos = su.getColeccionDeProductos();
        float suma=0f;
        suma = coleccionDeProductos.stream().map((p) -> (p.getPrecio()*p.getNumUnidades())).reduce(suma, (accumulator, _item) -> accumulator + _item);
        return suma;
    }

    
    
    public void cargarInfo(ArrayList <String> clientes, ArrayList <String> productos) {
        ArrayList <Producto> coleccionDeProductos = new ArrayList<>();
        ArrayList <Cliente> coleccionDeClientes =  new ArrayList<>();
        int numPersonas = clientes.size() ;
         int numProductos = productos.size() ;
        String[] fragmentos,partes;
        
       
        for(int i=0;i<numPersonas;i++)
        {
            if(clientes.get(i).isEmpty()){
                continue;}
            fragmentos=clientes.get(i).split("#");
            Cliente c= new Cliente(fragmentos[0],fragmentos[1],fragmentos[2],fragmentos[3],fragmentos[4]);
            coleccionDeClientes.add(c);
            
        }
        
        for(int i=0;i<numProductos;i++)
        {
            if(productos.get(i).isEmpty()){
                continue;}
            partes=productos.get(i).split("#");
            Producto p = new Producto (partes[0],Float.parseFloat(partes[1]),Float.parseFloat(partes[2]),Integer.parseInt(partes[3]));
            coleccionDeProductos.add(p);
            
        }
    
    su.setColeccionDeClientes(coleccionDeClientes);
    su.setColeccionDeProductos(coleccionDeProductos);
    }

    public void arranque() {
        String route = System.getProperty("user.home")
                +File.separator
                +"Escritorio"
                +File.separator
                +"Super17";
        su.setRuta(route);
        File Scliente=new File (route+File.separator+"clientes.bin");
        File clientes=new File (route+File.separator+"clientes.bin");
        File Sproducto=new File (route+File.separator+"productos.bin");
        File productos=new File (route+File.separator+"productos.bin");
        Path clientes2,productos2;
        ArrayList <String> clientesL = new ArrayList<>();
        ArrayList <Cliente> Clientebin = new ArrayList<>();
        ArrayList <String> productosL = new ArrayList<>();        
        ArrayList <Producto> Productobin = new ArrayList<>();
        
        if(!clientes.exists()){//csv
            clientes=new File(route+File.separator+"clientes.csv");
            clientes2 = clientes.toPath();
            try{ 
                clientesL = (ArrayList<String>) Files.readAllLines(clientes2); 
            }catch(IOException io){
                System.err.printf("%n%nERROR: no se pudo leer el archivo productos");
                System.exit(-1);
            }
        }
        else{//bin
            clientes2 = clientes.toPath();
            try{
                FileInputStream fis = new FileInputStream(clientes2.toFile());
                BufferedInputStream bis = new BufferedInputStream(fis);
                try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                    Clientebin = (ArrayList<Cliente>) ois.readObject();
                }
            }catch (IOException | ClassNotFoundException ex) {
                System.out.println("No fue posible leer el archivo");
                System.out.println(ex.toString());
            }
        }
        if(!productos.exists()){//csv
            productos=new File(route+File.separator+"productos.csv");
            productos2 = productos.toPath();
            try{ 
                productosL = (ArrayList<String>) Files.readAllLines(productos2); 
            }catch(IOException io){
                System.err.printf("%n%nERROR: no se pudo leer el archivo productos");
                System.exit(-1);
            }             
        }
        else{//bin
            productos2 = productos.toPath();
            try{
                FileInputStream fis = new FileInputStream(productos2.toFile());
                BufferedInputStream bis = new BufferedInputStream(fis);
                try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                    Productobin =  (ArrayList<Producto>) ois.readObject();
                }
            }catch (IOException | ClassNotFoundException ex) {
                System.out.println("No fue posible leer el archivo");
                System.out.println(ex.toString());
            }
        }
        if(Scliente.exists() && Sproducto.exists()){
            su.setColeccionDeClientes(Clientebin);
            su.setColeccionDeProductos(Productobin);
        }else{
            this.cargarInfo(clientesL,productosL);
        }
    }
    public String[] impApeNom(){
        if(su.getColeccionDeClientes()==null){
            return null;
        }
        int i=0;
        ArrayList <Cliente> lista = su.getColeccionDeClientes();
        Collections.sort(lista,new OrdenarClientePorApellidos());
        String[]vector= new String [lista.size()];
        Cliente cl;
        Iterator iter = lista.iterator();
        while(iter.hasNext()){
            cl = (Cliente) iter.next();
            vector[i]= cl.ImprimeClinte();
            i++ ;           
        }
        return vector;
    }
    
    
    public String[] impAlfabeticamente() {
        if(su.getColeccionDeProductos()==null){
            return null;
        }
        int i=0;
        ArrayList <Producto> lista = su.getColeccionDeProductos();
        Collections.sort(lista,new OrdenarProductoAlfabeticamente());
        String[]vector= new String [lista.size()];
        Producto cl;
        Iterator iter = lista.iterator();
        while(iter.hasNext()){
            cl = (Producto) iter.next();
            vector[i]= cl.ImprimeProducto();
            i++ ;           
        }
        return vector;
    }

    public String[] impIVAPrecio() {
        if(su.getColeccionDeProductos()==null){
            return null;
        }
        int i=0;
        ArrayList <Producto> lista = su.getColeccionDeProductos();
        Collections.sort(lista,new OrdenarProductoPorIvaPrecio());
        String[]vector= new String [lista.size()];
        Producto cl;
        Iterator iter = lista.iterator();
        while(iter.hasNext()){
            cl = (Producto) iter.next();
            vector[i]= cl.ImprimeProducto();
            i++ ;           
        }
        return vector;
    }


    public String[] impDNI() {
        if(su.getColeccionDeClientes()==null){
            return null;
        }
        int i=0;
        ArrayList <Cliente> lista = su.getColeccionDeClientes();
        Collections.sort(lista,new OrdenarClientePorDni());
        String[]vector= new String [lista.size()];
        Cliente cl;
        Iterator iter = lista.iterator();
        while(iter.hasNext()){
            cl = (Cliente) iter.next();
            vector[i]= cl.ImprimeClinte();
            i++ ;           
        }
        return vector;
    }

    public boolean comprobarDisponibilidad(String producto) {
        if(su.getColeccionDeProductos()==null){
            System.out.println("Error no se pudo imprimir los productos");
            return false;
        }
        ArrayList coleccion = su.getColeccionDeProductos();
        for (Iterator it = coleccion.iterator(); it.hasNext();) {
            Producto p = (Producto) it.next();
            if(p.comprobarSiExiste(producto)){
                return true;
            }
        }
        return false;
    }

    public boolean comprobarCantidad(String producto, int cantidad) {
        ArrayList<Producto> coleccion = su.getColeccionDeProductos();
        
        return coleccion.stream().anyMatch((p) -> (p.comprobarCantidad(producto,cantidad)));
    }

    public void comprar(ArrayList<String> listaDeLaCompra, ArrayList<Integer> numCantidades) {
        ArrayList<Producto> coleccion = su.getColeccionDeProductos();
        for(int i=0;i<listaDeLaCompra.size();i++){
            for(Producto p: coleccion){
                p.comprar(listaDeLaCompra.get(i), numCantidades.get(i));
            }
        }
        su.setColeccionDeProductos(coleccion);
    }

    public float obtenerPercioDe(String nombre) {
        ArrayList<Producto> coleccion = su.getColeccionDeProductos();
        for(Producto p: coleccion){
            if(nombre.equals(p.getNombre())){
                return (p.getPrecio());
            }
        }
        return 0;
    }

    
    public float basePara(ArrayList<String> listaDeLaCompra, ArrayList<Integer> numCantidades,float iva) {
        float resultado = 0f;
        
        ArrayList<Producto> coleccion = su.getColeccionDeProductos();
        for(int i=0;i<listaDeLaCompra.size();i++){
            for(Producto p: coleccion){
                if(listaDeLaCompra.get(i).equals(p.getNombre())){
                    if(p.getIva()==iva){
                        resultado+=p.getPrecio()*numCantidades.get(i);
                    }
                }
                
            }
        }
        return resultado;
    }

    public void generarTicket(float suma,int identificador) {
        ArrayList<Ticket> coleccion= su.getColeccionDeTickets();
        Ticket t=new Ticket(identificador,suma);        
        coleccion.add(t);
        su.setColeccionDeTickets(coleccion);        
    }

    public int generarNumfac() {
        int maxTicket;
        ArrayList<Ticket> listaTicket=su.getColeccionDeTickets();
        if(listaTicket.isEmpty()){
            maxTicket=10000000;            
        }else{
            int temp=10000000;
            for(Ticket t : listaTicket){
                if(t.getIdentifcador()>temp){
                    temp=t.getIdentifcador();
                }
            }
            maxTicket=temp;
        }
        
        ArrayList <Cliente> listaCliente=su.getColeccionDeClientes();
        int maxFac=10000000;
        for(Cliente c : listaCliente){
          
            ArrayList <Factura> listaFactura= c.getColeccionDeFacturas();
            if(!listaFactura.isEmpty()){
                int temp2=10000000;
                for (Factura f : listaFactura){
                    if(f.getIndentificador()>temp2){
                        temp2=f.getIndentificador();
                    }
                }
                if (temp2>maxFac){
                    maxFac=temp2;
                }
            }
        }
        if(maxFac > maxTicket){
            return (maxFac+1);
        }else{
            return (maxTicket+1);
        }
    }

    public boolean comprobarExistenciaDelCliente(String dni) {
        ArrayList <Cliente> listaCliente = su.getColeccionDeClientes();
        for(Cliente c : listaCliente){
            if(dni.equals(c.getDni())){
                return c.getEstado().equals("activo");
            }
        }
        return false;
    }

    public String getFormatoFac(String dni) {
        ArrayList <Cliente> listaCliente=su.getColeccionDeClientes();
        for(Cliente c : listaCliente){
            if(dni.equals(c.getDni())){
                return String.format("Cliente: %s %s\t%s%n%s%n%n",c.getNombre(),c.getApellido(),dni,c.getDireccion());
            }
        }
        return null;
    }

    public void generarFactura(float suma, int identificador, String dni) {
        ArrayList<Cliente> coleccion= su.getColeccionDeClientes();
        coleccion.stream().filter((c) -> (dni.equals(c.getDni()))).forEachOrdered((c) -> {
            ArrayList <Factura> listaFactura = c.getColeccionDeFacturas();
            Factura f = new Factura(identificador,dni,suma);
            listaFactura.add(f);
            c.setColeccionDeFacturas(listaFactura);
        });
    }

    public void terminarPrograma() {
        String route = su.getRuta();
        
        File clientes=new File (route+File.separator+"clientes.bin");
        File productos=new File (route+File.separator+"productos.bin");
        Path clientes2,productos2;
        clientes2 = clientes.toPath();
        productos2 = productos.toPath();
        try {//ForClientes
            FileOutputStream fos = new FileOutputStream(clientes2.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(su.getColeccionDeClientes());
            }
        } catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo");
            System.out.println(ex.toString());
        }
        try {//For
            FileOutputStream fos = new FileOutputStream(productos2.toFile());
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(su.getColeccionDeProductos());
            }
        } catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo");
            System.out.println(ex.toString());
        }
    }

    

    

    
    
}
