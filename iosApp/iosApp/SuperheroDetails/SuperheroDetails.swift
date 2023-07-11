//
//  SuperheroDetails.swift
//  iosApp
//
//  Created by Bharat Kumar on 29/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//
import SwiftUI
import shared
import KMMViewModelSwiftUI
import Kingfisher


struct SuperheroDetailsScreen: View {
    var navigateBack: () -> Void
    var superhero:SuperheroDetailsDataHolder?
    
    var body: some View {
        
        if let unWrappedSuperhero = superhero{
                ScrollView {
                    VStack {
                        ZStack(alignment:.top){
                            KFImage(URL(string: unWrappedSuperhero.imageUrl))
                                .placeholder{
                                    ProgressView()
                                }
                                .resizable()
                                .blur(radius: 5)
                                .frame(width: UIScreen.main.bounds.width, height: 300)
                            
                            
                            KFImage(URL(string: unWrappedSuperhero.imageUrl))
                                .resizable()
                                .frame(width: UIScreen.main.bounds.width * 0.60, height: UIScreen.main.bounds.width * 0.80)
                                .clipShape(RoundedRectangle(cornerRadius: 20))
                                .shadow(radius: 10)
                                .alignmentGuide(.bottom) { $0[.bottom] }
                                .padding(.top,60)
                                
                        }
                        
                        Text(unWrappedSuperhero.name)
                            .font(.title)
                            .fontWeight(.bold)
                            .foregroundColor(.white)
                            .multilineTextAlignment(.center)
                        
                        Text("BIO")
                            .font(.system(size: 22))
                            .fontWeight(.bold)
                            .foregroundColor(.white)
                            .padding(.top, 1)
                        
                        Text(unWrappedSuperhero.bio)
                            .font(.system(size: 18))
                            .fontWeight(.semibold)
                            .foregroundColor(.white)
                            .multilineTextAlignment(.center)
                            .padding(.horizontal,8)
                            .padding(.top,1)
                        
                        HStack(spacing: 40) {
                            VStack {
                                Image("height_logo")
                                    .resizable()
                                    .frame(width: 30, height: 30)
                                Text("Height: \(unWrappedSuperhero.height)")
                                    .foregroundColor(.white)
                                    .font(.system(size: 18))
                            }
                            
                            VStack {
                                Image("weight_logo")
                                    .resizable()
                                    .frame(width: 30, height: 30)
                                Text("Weight: \(unWrappedSuperhero.weight)")
                                    .foregroundColor(.white)
                                    .font(.system(size: 18))
                            }
                        }
                        .frame(maxWidth: .infinity)
                        .padding(.top, 20)
                        
                        SuperheroStatsIndicator(percent: Float(unWrappedSuperhero.strength), color: Color(red: 0.957, green: 0.263, blue: 0.212), label: "Strength")
                        SuperheroStatsIndicator(percent: Float(unWrappedSuperhero.power), color: Color(red: 0.612, green: 0.153, blue: 0.690), label: "Power")
                        SuperheroStatsIndicator(percent: Float(unWrappedSuperhero.speed), color: Color(red: 1.000, green: 0.922, blue: 0.227), label: "Speed")
                        SuperheroStatsIndicator(percent: Float(unWrappedSuperhero.intelligence), color: Color(red: 0.012, green: 0.663, blue: 0.957), label: "Intelligence")
                        SuperheroStatsIndicator(percent: Float(unWrappedSuperhero.combat), color: Color(red: 0.220, green: 0.557, blue: 0.235), label: "Combat")
                    }
                    .padding()
                    .background(Color.primary)
                    
                }
                .ignoresSafeArea()
            }
        
        
    
    }
}

struct SuperheroStatsIndicator: View {
    @State private var animationPlayed = false
    let percent: Float
    let color: Color
    let label: String
    
    var body: some View {
        Text("\(label) \(Int(percent))")
            .foregroundColor(.white)
            .padding(.top, 15)
        
        VStack(alignment: .leading) {

                  ZStack(alignment: .leading) {
                      RoundedRectangle(cornerRadius: 3)
                          .fill(Color(.systemGray6))
                      RoundedRectangle(cornerRadius: 3)
                          .fill(color)
                          .frame(width: animationPlayed ? CGFloat(percent*3) : 0, alignment: .leading)
                          .animation(.easeInOut(duration: 2), value: animationPlayed)
                  }
                  .frame(width: 300, height: 12)
                  .onAppear {
                      animationPlayed.toggle()
                  }
              }
          }
}

struct SuperheroDetailsScreen_Previews: PreviewProvider {
    static var previews: some View {
        SuperheroDetailsScreen(navigateBack: {},superhero: SuperheroDetailsDataHolder(name: "Bharat", bio: "Abbaa", imageUrl: "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/4-abomination.jpg", strength: 88, durability: 65, combat: 44, power: 34, speed: 55, intelligence: 66, weight: "78", height: "55"
                                                                                     ) )
    }
}

