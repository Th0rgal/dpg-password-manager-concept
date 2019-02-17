require 'sha3'

s = SHA3::Digest.new(:sha256)
s << "test"
puts s.hexdigest
puts s.hexdigest.to_i 16