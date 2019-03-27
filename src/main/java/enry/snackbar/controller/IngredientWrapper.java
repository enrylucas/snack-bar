/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enry.snackbar.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
/**
 *
 * @author enry_
 */
@Data
public class IngredientWrapper {
    public List<Integer> ingredientsId = new ArrayList<>();
    public List<Integer> ingredientsQty = new ArrayList<>();
}
