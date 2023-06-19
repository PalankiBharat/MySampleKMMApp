//
//  SuperHeroCard.swift
//  iosApp
//
//  Created by Bharat Kumar on 17/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Kingfisher

struct SuperheroCard: View {
    var imageUrl: String
    var title: String
    
  
    var body: some View {
        ZStack(alignment: .bottomLeading) {
            KFImage(URL(string: imageUrl))
                            .placeholder {
                                ProgressView()
                            }
                            .resizable()
                            .onFailure{ error in
                                Image(systemName: "photo")
                                    .resizable()
                                    .scaledToFill()
                                    .frame(maxWidth: .infinity, maxHeight: 250)
                            }
                            .scaledToFill()
                            .frame(maxWidth: .infinity, maxHeight: 250)
                            
            
            LinearGradient(
                gradient: Gradient(
                    colors: [Color.clear, Color.black]
                ),
                startPoint: .init(x: 0, y: 0.5),
                endPoint: .init(x: 0, y: 1)
            )
            
            Text(title)
                .foregroundColor(.white)
                .font(.system(size: 24, weight: .bold))
                .padding(16)
        }
        .frame(maxWidth: .infinity)
        .frame(height: 250)
    }
}

struct SuperHeroCard_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroCard(imageUrl: "", title: "Superhero")
    }
}
