import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar2 from '../../Components/Navbar2';

const EditProfilePage = () => {
  const [name, setName] = useState('Meera Kapur');
  const [email, setEmail] = useState('meera35@gmail.com');
  const [password, setPassword] = useState('***********');

  const navigate = useNavigate();

  const handleNameSave = () => {
    console.log('Name Saved:', name);
    navigate('/profilepage');
  };

  const handleEmailSave = () => {
    console.log('Email Saved:', email);
    navigate('/profilepage');
  };

  const handlePasswordSave = () => {
    console.log('Password Saved:', password);
    navigate('/profilepage');
  };

  return (
    <div className="bg-gradient-to-b from-[#090e2d] to-[#111827] min-h-screen text-white">
      <Navbar2 />

      <div className="min-h-screen flex flex-col items-center justify-center px-6 py-10">
        <h1 className="text-3xl font-semibold mb-8">Edit Profile</h1>

        <div className="w-full max-w-xl space-y-6">

          {/* Name */}
          <div>
            <label className="block mb-2 text-lg">Name</label>
            <div className="flex">
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="flex-grow p-3 rounded-l-lg bg-blue-950 text-white"
              />
              <button
                onClick={handleNameSave}
                className="bg-blue-900 hover:bg-blue-700 px-6 rounded-r-lg"
              >
                Save
              </button>
            </div>
          </div>

          {/* Email */}
          <div>
            <label className="block mb-2 text-lg">Email</label>
            <div className="flex">
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="flex-grow p-3 rounded-l-lg bg-blue-950 text-white"
              />
              <button
                onClick={handleEmailSave}
                className="bg-blue-900 hover:bg-blue-700 px-6 rounded-r-lg"
              >
                Save
              </button>
            </div>
          </div>

          {/* Password */}
          <div>
            <label className="block mb-2 text-lg">New Password</label>
            <div className="flex">
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="flex-grow p-3 rounded-l-lg bg-blue-950 text-white"
              />
              <button
                onClick={handlePasswordSave}
                className="bg-blue-900 hover:bg-blue-700 px-6 rounded-r-lg"
              >
                Save
              </button>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
};

export default EditProfilePage;
