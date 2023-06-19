//
//  SuperheroListingScreen.swift
//  iosApp
//
//  Created by Bharat Kumar on 16/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import KMMViewModelSwiftUI


struct SuperheroListingScreen: View {
    
    @StateViewModel var viewModel = ViewModels().getHomeViewModel()
    
    var body: some View {
        let superheroList = self.viewModel.uiStates.newList as! [SuperheroDetailsDataHolder]
        

        let gridItems = [
               GridItem(.flexible()),
               GridItem(.flexible())
           ]
        
        LazyHGrid(rows: gridItems){
            ForEach(superheroList, id:\.name){superhero in
                SuperheroCard(imageUrl: superhero.imageUrl , title:superhero.name )
            }
           
        }
    }
}

struct SuperheroListingScreen_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroListingScreen()
    }
}


