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
   
    @StateViewModel var viewModel = KoinHelperIOS().getSuperheroListingViewModel()
    
    
    var body: some View {
        let superheroList = self.viewModel.superheroListingStates
        
        
        let gridItems = [
            GridItem(.adaptive(minimum: 100)),
            GridItem(.adaptive(minimum: 100))
        ]
        
        ScrollView{
            LazyVGrid(columns: gridItems){
                ForEach(superheroList, id:\.name){superhero in
                    SuperheroCard(imageUrl: superhero.imageUrl , title:superhero.name )
                }
            }
        }.onAppear{
            UIScrollView.appearance().bounces = false
        }
    }
 
    
}

struct SuperheroListingScreen_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroListingScreen()
    }
}


