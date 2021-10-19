package com.example.lambda;

import com.example.lambda.Model.Request;
import com.example.lambda.Model.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("HandlerLambda")
public class HandlerLambda implements Function<Request, Response> {

    Response resp = new Response();

    @Override
    public Response apply(Request request) {

        String requestGroups = request.getGroups();

        String response = calculateCapacityBus(requestGroups);

        resp.setSizes(response);

        return resp;
    }

    private static String calculateCapacityBus(String requestGroups) {
        //Convertimos el string de grupos en una lista de enteros
        List<Integer> groups = Arrays.asList(requestGroups.split(",")).stream().map(x -> new Integer(x)).collect(Collectors.toList());
        //Acá guardaremos los tamaños de los buses que sirven para los grupos
        List<Integer> sizesBus = new ArrayList();
        //Total de pasajeros de todos los grupos
        Integer totalPassengers = groups.stream().mapToInt(Integer::intValue).sum();
        //Valor del grupo con mas pasajeros , ya que el bus minimo debe tener una capacidad igual al grupo más grande
        Optional<Integer> largestGroup = groups.stream().max(Integer::compare);
        Integer capacitydBus = largestGroup.get();
        while (capacitydBus <= totalPassengers) {
            Integer sumPassengers = 0;
            for (int i = 0; i < groups.size(); i++) {
                sumPassengers += groups.get(i);
                //Siempre debe ir lleno el bus en cada viaje para que la capacidad sea una candidata de respuesta
                if (sumPassengers.equals(capacitydBus)) {
                    sumPassengers = 0;
				/*Si la suma de grupos sobrepasa la capacidad del bus, y como el bus no puede llevar grupos incompletos ,
				la capacidad no es candidata de respuesta*/
                } else if (sumPassengers > capacitydBus) {
                    break;
                }
            }
            if (sumPassengers == 0) {
                sizesBus.add(capacitydBus);
            }
            capacitydBus++;
        }
        return sizesBus.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

}
