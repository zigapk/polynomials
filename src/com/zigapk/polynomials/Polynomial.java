package com.zigapk.polynomials;

import java.util.ArrayList;

/**
 * Created by zigapk on 5.2.2016.
 */
public class Polynomial {
    public ArrayList<Integer> particles = new ArrayList<>();

    public Polynomial fromStr(String str){
        str = str.replace(" ", "");
        int from = 0;
        for(int i = 0; i <= str.length(); i++){
            if (i == str.length() || str.charAt(i) == '+' || str.charAt(i) == '-'){
                String particle = str.substring(from, i);
                parseParticle(particle);
                from = i;
            }
        }
        return this;
    }

    private void parseParticle(String particle){
        if (particle != null && particle != ""){
            int level = 0;
            if (particle.contains("x")) {
                try {
                    level = Integer.parseInt(particle.substring(particle.indexOf("^")+1));
                }catch (Exception e){
                    level = 1;
                }
            }

            int value = 0;
            try {
                String substr = "";
                if (particle.contains("x")) substr = particle.substring(0, particle.indexOf("x"));
                else substr = particle;
                if (substr.equals("-") || substr.equals("+")) substr += "1";
                value = Integer.parseInt(substr);
            }catch (Exception e){}

            addToArray(level, value);
        }
    }

    private void addToArray(int level, int value){
        while (particles.size() < level + 1){
            particles.add(0);
        }
        particles.set(level, particles.get(level)+value);
    }

    public String toStr(){
        String result = "";
        for (int i = particles.size()-1; i >= 0; i--) {
            if (particles.get(i) != 0){
                if (i != particles.size()-1 && particles.get(i) > 0) result += "+";
                result += particles.get(i);
                String addition = "x^"+i;
                if (!addition.equals("x^0")) result += addition;
            }
        }
        result = result.replace("1x", "x");
        result = result.replace("^1", "");
        return result;
    }

    public int level(){
        return particles.size();
    }

    public Polynomial add(Polynomial a){
        return add(a, this);
    }

    public static Polynomial add(Polynomial a, Polynomial b){
        Polynomial result = new Polynomial();
        int level = Math.max(a.level(), b.level());
        for (int i = 0; i <= level; i++) {
            int m = 0;
            int n = 0;
            try {
                m = a.particles.get(i);
            }catch (Exception e){}
            try {
                n = b.particles.get(i);
            }catch (Exception e){}
            result.addToArray(i, m + n);
        }
        return result;
    }

    public Polynomial multiply(int n){
        return multiply(this, n);
    }

    public static Polynomial multiply(Polynomial a, int n){
        for (int i = 0; i < a.particles.size(); i++) {
            a.particles.set(i, a.particles.get(i) * n);
        }
        return a;
    }

    public Polynomial multiply(Polynomial a){
        return multiply(this, a);
    }

    public static Polynomial multiply(Polynomial a, Polynomial b){
        ArrayList<Polynomial> toSum = new ArrayList<>();
        for (int i = 0; i < a.particles.size(); i++) {
            Polynomial temp = new Polynomial();
            for (int j = 0; j < b.particles.size(); j++) {
                temp.addToArray(i+j, a.particles.get(i)*b.particles.get(j));
            }
            toSum.add(temp);
        }
        Polynomial result = new Polynomial();
        for (Polynomial p : toSum) {
            result = result.add(p);
        }
        return result;
    }
}
