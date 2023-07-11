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
        ZStack(alignment: .bottom) {
            GeometryReader{geomentry in
                    KFImage(URL(string: imageUrl))
                                    .placeholder {
                                        ProgressView()
                                    }
                                    .onFailure{ error in
                                        Image(systemName: "photo")
                                    }
                                    .resizable()
                        .scaledToFill()
                        .frame(width: geomentry.size.width, height: geomentry.size.height)
                    
                
              
            
                LinearGradient(
                    gradient: Gradient(
                        colors: [Color.clear, Color.black]
                    ),
                    startPoint: .init(x: 0, y: 0.5),
                    endPoint: .init(x: 0, y: 1)
                ).frame(width: geomentry.size.width, height: geomentry.size.height)
                    .edgesIgnoringSafeArea(.all)
                    .padding(EdgeInsets.init(top: 0, leading: 0, bottom: 0, trailing: 0))
            
            }.frame(minHeight: 250)
        
                Text(title)
                    .foregroundColor(.white)
                    .font(.system(size: 24, weight: .bold))
                    .padding(12)
                    .frame(maxWidth: .infinity,alignment: .leading)
            
            
        
        }

        .background(Color.red)
        
    
    }
}

struct SuperHeroCard_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroCard(imageUrl: "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/731-zoom.jpg", title: "Super hero Name")
    }
}
