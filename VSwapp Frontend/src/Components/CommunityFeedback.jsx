import React from 'react'

export const CommunityFeedback = () => {
const feedbacks= [
    { desc: "VSwapp gave me the push I needed to share my skills and grow with others. Thanks VSwapp❤", name: "John Doe" },
    { desc: "An ideal space for curious minds to grow,connect,and exchange knowledge🙌.", name: "Meera Kapur" },
    { desc: "Joining VSwapp opened doors to learning new things and building lifelong friendships🤝.", name: "Miona" },
  ];

  return (
    <section className="py-8 px-6 text-center">
  <h2 className="text-4xl text-center text-white mb-30 mt-10">Community Feedback</h2>
  
  <div className="ml-50 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-30 place-items-center mt-10">
    
  
    {feedbacks.map((feedback, i) => (
      <div
        key={i}
        className="bg-gray-800 w-96 h-56 p-6 rounded-3xl hover:shadow-lg transition flex flex-col justify-center"
      >
        <h3 className="text-base font-semibold mb-2 text-white">{feedback.desc}</h3>
        <p className="text-gray-400 text-sm text-right">{feedback.name}</p>
      </div>
    ))}
  </div>
  
</section>
  )
}

export default CommunityFeedback