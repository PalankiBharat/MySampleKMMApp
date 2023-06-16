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
    
    var repo = AppRepository by inject()
    
    @StateViewModel var viewModel = SuperheroListingViewModel(repository:repo)

    
    var body: some View {
        
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct SuperheroListingScreen_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroListingScreen()
    }
}


